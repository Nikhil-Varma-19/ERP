package com.example.erp.backend.controllers;

import com.example.erp.backend.advices.ApiResponse;
import com.example.erp.backend.dtos.AddUserDto;
import com.example.erp.backend.dtos.AllUserDto;
import com.example.erp.backend.dtos.PageResponseDto;
import com.example.erp.backend.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<String>> addUser(@Valid @RequestBody AddUserDto addUserDto){
        String result= userService.addUser(addUserDto);
        return  ResponseEntity.ok(ApiResponse.success(result));
    }

    @GetMapping
    public  ResponseEntity<ApiResponse<PageResponseDto<AllUserDto>>> getUsers(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){

        PageResponseDto<AllUserDto> users=userService.getUsers(search,page,size);
        return ResponseEntity.ok(ApiResponse.success(users));
    }

}
