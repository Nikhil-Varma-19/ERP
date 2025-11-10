package com.example.erp.backend.services;

import com.example.erp.backend.dtos.PageResponseDto;
import com.example.erp.backend.entities.Technology;

public interface TechnologyService {
    String addTechnology(String name);
    String updateTechnology(String name,Long id);
    String deleteTechnology(Long id);
    PageResponseDto<?> getAllTechnology(String search,int page,int size);
    Technology getById(Long id);

}
