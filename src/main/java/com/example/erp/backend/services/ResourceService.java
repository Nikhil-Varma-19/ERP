package com.example.erp.backend.services;

import com.example.erp.backend.dtos.PageResponseDto;
import com.example.erp.backend.dtos.resource_dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ResourceService {
 String addResource(AddResourceDto addResourceDto, MultipartFile file);

 String deleteResource(Long id);

 String updateResource(Long id, UpdateResourceDto updateResourceDto, MultipartFile file);

 ResourceDetailDTO getById(Long id);

 PageResponseDto<AllResourceDTO> getAllResource(String search, int page, int size);


}
