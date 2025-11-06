package com.example.erp.backend.mapper;

import com.example.erp.backend.dtos.AddUserDto;
import com.example.erp.backend.dtos.UserDto;
import com.example.erp.backend.entities.User;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(target = "password",expression = "java(passwordEncoder.encode(addUserDto.getPassword()))")
    User addUserDtoToUser(AddUserDto addUserDto, @Context PasswordEncoder passwordEncoder);

    UserDto userToUserDto(User user);
}
