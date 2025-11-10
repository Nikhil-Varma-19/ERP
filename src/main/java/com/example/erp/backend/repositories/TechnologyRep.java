package com.example.erp.backend.repositories;

import com.example.erp.backend.dtos.TechnologyData;
import com.example.erp.backend.entities.Technology;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TechnologyRep extends JpaRepository<Technology,Long> {
    Optional<Technology> findByIdAndIsActiveTrue(Long id);

    @Query("select t.id as id,t.name as name from Technology t where t.isActive=true ")
    Page<TechnologyData> findByIsActiveTrue(Pageable pageable);

    @Query("select t.id,t.name from Technology t where t.isActive=true and t.name LIKE %?1%")
    Page<TechnologyData>findByIsActiveTrueAndName(String name,Pageable pageable);

    @Query("select case when count(t.id) > 0 then true else false end\n" +
            "    from Technology t\n" +
            "    where t.isActive = true\n" +
            " and t.id !=?2     and lower(t.name) like lower(concat('%', ?1, '%'))")
    boolean existsByNameAndIsActiveTrueAndIdNot(String name,Long id);

}
