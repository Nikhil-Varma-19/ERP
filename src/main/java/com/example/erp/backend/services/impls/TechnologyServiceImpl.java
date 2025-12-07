package com.example.erp.backend.services.impls;

import com.example.erp.backend.dtos.PageResponseDto;
import com.example.erp.backend.dtos.TechnologyData;
import com.example.erp.backend.entities.Technology;
import com.example.erp.backend.exceptions.AlreadyPresent;
import com.example.erp.backend.exceptions.BadRequest;
import com.example.erp.backend.exceptions.DataNotFound;
import com.example.erp.backend.repositories.ResourceSkillResp;
import com.example.erp.backend.repositories.TechnologyRep;
import com.example.erp.backend.services.TechnologyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TechnologyServiceImpl implements TechnologyService {
    private final TechnologyRep technologyRep;
    private final ResourceSkillResp resourceSkillResp;

    @Override
    public String addTechnology(String name) {
        if(technologyRep.existsByNameAndIsActiveTrueAndIdNot(name,0L)){
            throw  new AlreadyPresent("Technology is present");
        }
        Technology technology= Technology.builder().name(name).build();
        technologyRep.save(technology);
        return "Technology created successfully";
    }

    @Override
    public String updateTechnology(String name, Long id) {
        Technology technology=technologyRep.findByIdAndIsActiveTrue(id).orElseThrow(()->new DataNotFound("Technology not found"));
        if(technologyRep.existsByNameAndIsActiveTrueAndIdNot(name,id)){
            throw  new AlreadyPresent("Technology is present");
        }
        technology.setName(name);
        technologyRep.save(technology);
        return "Technology updated successfully";
    }

    @Override
    public String deleteTechnology(Long id) {
        Technology technology=technologyRep.findByIdAndIsActiveTrue(id).orElseThrow(()->new DataNotFound("Technology not found"));
        technology.setIsActive(false);
        technologyRep.save(technology);
        return "Technology deleted Successfully";
    }

    @Override
    public PageResponseDto<?> getAllTechnology(String search, int page, int size) {
        Pageable pageable= PageRequest.of(page, size, Sort.by("id").descending());
        Page<TechnologyData> technologies=(search == null || search.isBlank() ) ? technologyRep.findByIsActiveTrue(pageable) : technologyRep.findByIsActiveTrueAndName("%"+search+"%",pageable);
        PageResponseDto<TechnologyData> response=new PageResponseDto<>();
        response.setResults(technologies.stream().toList());
        response.setCurrentPage(technologies.getNumber());
        response.setTotalRecords(technologies.getTotalElements());
        response.setTotalPages(technologies.getTotalPages());
        return response;
    }

    @Override
    public Technology getById(Long id) {
        return technologyRep.findByIdAndIsActiveTrue(id).orElseThrow(()->new DataNotFound("Technology not found"));
    }

    @Override
    public List<Technology> allTechnologyPresent(List<Long> ids) {
      List<Technology> technologies=  technologyRep.findByIsActiveTrueAndIdIn(ids);
      if(ids.size() != technologies.size()) throw  new DataNotFound("Technology not found");
        return technologies;
    }

    @Override
    public void deleteTechnologyBulk(List<Long> ids) {
      int updateCount= resourceSkillResp.updateResourceSkillIsActiveFalse(ids);
      if(updateCount != ids.size()) throw new BadRequest("Update skill count is not matched");
    }


}
