package com.example.erp.backend.mapper;

import com.example.erp.backend.dtos.resource_dto.AddResourceDto;
import com.example.erp.backend.dtos.resource_dto.AllResourceDTO;
import com.example.erp.backend.dtos.resource_dto.ResourceDetailDTO;
import com.example.erp.backend.dtos.resource_dto.UpdateResourceDto;
import com.example.erp.backend.entities.Resource;
import com.example.erp.backend.entities.ResourceSkill;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ResourceMapper {
    @Mapping(target = "skills", ignore = true)
    Resource dtoToEntity(AddResourceDto addResourceDto);

    @Mapping(target = "skills", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDtoEntity(UpdateResourceDto updateResourceDto,@MappingTarget Resource resource);

    @Mapping(source = "vendor.name", target = "vendorName")
    @Mapping(source = "skills", target = "technologies", qualifiedByName = "skillsToTechnologyNames")
    ResourceDetailDTO toResourceDetailDTO(Resource resource);


    @Mapping(target = "vendorName", source = "vendor.name")
    @Mapping(target = "vendorId", source = "vendor.id")
    @Mapping(source = "skills", target = "technologies", qualifiedByName = "skillsToTechnologyNames")
    AllResourceDTO toAllResourceDTO(Resource resource);


    List<AllResourceDTO> allResourceData(List<Resource> resources);


    @Named("skillsToTechnologyNames")
    default List<String> skillsToTechnologyNames(List<ResourceSkill> skills){
        if(skills == null || skills.isEmpty()) return List.of();

        return skills.stream()
                .filter(skill -> skill.getIsActive() != false)
                .map(skill -> skill.getTechnology().getName())
                .collect(Collectors.toList());
    }
}
