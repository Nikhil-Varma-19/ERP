package com.example.erp.backend.services;

import com.example.erp.backend.dtos.AddUserDto;
import com.example.erp.backend.dtos.UpdateUserDto;
import com.example.erp.backend.dtos.UserDto;
import com.example.erp.backend.dtos.PageResponseDto;

public interface UserService {
    String addUser(AddUserDto addUserDto);

    PageResponseDto<UserDto> getUsers(String search, int page, int size);

    UserDto getUserById(Long id);

    String updateUser(Long id, UpdateUserDto updateUserDto);


}
