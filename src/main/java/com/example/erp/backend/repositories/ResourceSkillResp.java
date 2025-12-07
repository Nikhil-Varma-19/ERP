package com.example.erp.backend.repositories;

import com.example.erp.backend.entities.ResourceSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ResourceSkillResp extends JpaRepository<ResourceSkill,Long> {

    @Modifying
    @Transactional
    @Query("UPDATE ResourceSkill rs set rs.isActive=false where rs.resource.id IN (:ids)")
    int updateResourceSkillIsActiveFalse(List<Long> ids);
}
