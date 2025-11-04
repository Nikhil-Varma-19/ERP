package com.example.erp.backend.services;

import com.example.erp.backend.dtos.AddUserDto;
import com.example.erp.backend.dtos.AllUserDto;
import com.example.erp.backend.dtos.PageResponseDto;

public interface UserService {
    String addUser(AddUserDto addUserDto);

    PageResponseDto<AllUserDto> getUsers(String search, int page, int size);
}
