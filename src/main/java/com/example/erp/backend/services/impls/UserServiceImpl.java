package com.example.erp.backend.services.impls;

import com.example.erp.backend.dtos.AddUserDto;
import com.example.erp.backend.dtos.UpdateUserDto;
import com.example.erp.backend.dtos.UserDto;
import com.example.erp.backend.dtos.PageResponseDto;
import com.example.erp.backend.entities.User;
import com.example.erp.backend.exceptions.AlreadyPresent;
import com.example.erp.backend.exceptions.DataNotFound;
import com.example.erp.backend.exceptions.NoData;
import com.example.erp.backend.mapper.UserMapper;
import com.example.erp.backend.repositories.UserRep;
import com.example.erp.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRep userRep;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public String addUser(AddUserDto addUserDto) {
        if(userRep.existsByEmail(addUserDto.getEmail())){
            throw new AlreadyPresent("Email is already exist.");
        }
        User newUser=userMapper.addUserDtoToUser(addUserDto,passwordEncoder);
        userRep.save(newUser);
        return "User created successfully";
    }

    @Override
    public PageResponseDto<UserDto> getUsers(String search, int page, int size) {
        Pageable pageable= PageRequest.of(page,size, Sort.by("id").descending());
        Page<User> users =(search == null || search.isBlank()) ? userRep.findByIsActiveTrue(pageable)
                : userRep.findByIsActiveTrueAndNameContainingIgnoreCaseOrIsActiveTrueAndEmailContainingIgnoreCase(search,search,pageable) ;

        if(users.toList().isEmpty()) throw  new NoData("No Data");

        PageResponseDto<UserDto> response=new PageResponseDto<UserDto>();
        response.setCurrentPage(users.getNumber());
        response.setTotalPages(users.getTotalPages());
        response.setTotalRecords(users.getTotalElements());
        response.setResults(users.stream().map(userMapper::userToUserDto).toList());
         return response;
    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> user=userRep.findByIdAndIsActiveTrue(id);
        if(user.isEmpty()) throw new DataNotFound("User Not Found");
        return userMapper.userToUserDto(user.get());
    }

    @Override
    public String updateUser(Long id, UpdateUserDto updateUserDto) {
        Optional<User> user=userRep.findByIdAndIsActiveTrue(id);
        if(user.isEmpty()) throw new DataNotFound("User Not Found");
        if(updateUserDto.getName() != null && !updateUserDto.getName().isBlank() )  user.get().setName(updateUserDto.getName());
        if(updateUserDto.getEmail() != null && !updateUserDto.getEmail().isBlank()) user.get().setEmail(updateUserDto.getEmail());
        userRep.save(user.get());
        return "Update Successfully";
    }


}
