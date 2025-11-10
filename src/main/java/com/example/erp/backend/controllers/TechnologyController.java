package com.example.erp.backend.controllers;

import com.example.erp.backend.advices.ApiResponse;
import com.example.erp.backend.dtos.PageResponseDto;
import com.example.erp.backend.services.TechnologyService;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/skill")
@Validated
public class TechnologyController {
    @Autowired
    private TechnologyService technologyService;

    @PostMapping
    public ResponseEntity<ApiResponse<String>> addTechnology(@RequestBody Map<String,String> obj){
        if(obj == null || obj.isEmpty() || !obj.containsKey("name") || obj.get("name").isBlank()){
            return ResponseEntity.badRequest().body(ApiResponse.error("Name is required."));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(technologyService.addTechnology(obj.get("name"))));
    }

    @PatchMapping("{id}")
    public ResponseEntity<ApiResponse<String>> updateTechnology(@PathVariable("id") @Min(1) Long id, @RequestBody Map<String,String> obj){
        if(obj == null || obj.isEmpty() || !obj.containsKey("name") || obj.get("name").isBlank()){
            return ResponseEntity.badRequest().body(ApiResponse.error("Name is required."));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(technologyService.updateTechnology(obj.get("name"),id)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<String>> deleteTechnology(@PathVariable("id") @Min(1) Long id){
        return ResponseEntity.ok(ApiResponse.success(technologyService.deleteTechnology(id)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponseDto<?>>> getAll(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return ResponseEntity.ok(ApiResponse.success(technologyService.getAllTechnology(search,page,size)));
    }

}
