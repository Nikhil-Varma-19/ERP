package com.example.erp.backend.controllers;

import com.example.erp.backend.advices.ApiResponse;
import com.example.erp.backend.dtos.AddUserDto;
import com.example.erp.backend.dtos.UpdateUserDto;
import com.example.erp.backend.dtos.UserDto;
import com.example.erp.backend.dtos.PageResponseDto;
import com.example.erp.backend.services.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<String>> addUser(@Valid @RequestBody AddUserDto addUserDto){
        String result= userService.addUser(addUserDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(result));
    }

    @GetMapping
    public  ResponseEntity<ApiResponse<PageResponseDto<UserDto>>> getUsers(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        PageResponseDto<UserDto> users=userService.getUsers(search,page,size);
        return ResponseEntity.ok(ApiResponse.success(users));
    }

    @GetMapping("{id}")
    public  ResponseEntity<ApiResponse<UserDto>> getUserById(@PathVariable  @Min(1) Long id){
        return  ResponseEntity.ok(ApiResponse.success(userService.getUserById(id)));
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<String>> updateUser(@PathVariable @Min(1) Long id, UpdateUserDto updateUserDto){
        return  ResponseEntity.ok(ApiResponse.success(userService.updateUser(id,updateUserDto)));
    }

}
