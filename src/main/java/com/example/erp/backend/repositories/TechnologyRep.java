package com.example.erp.backend.repositories;

import com.example.erp.backend.dtos.TechnologyData;
import com.example.erp.backend.entities.Technology;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TechnologyRep extends JpaRepository<Technology,Long> {
    Optional<Technology> findByIdAndIsActiveTrue(Long id);

    @Query("select t.id as id,t.name as name,count(rs.id) as resourceCount from Technology t  LEFT JOIN t.resources  rs ON (rs.isActive =true) where t.isActive=true GROUP BY t.id,rs.technology  ")
    Page<TechnologyData> findByIsActiveTrue(Pageable pageable);

    @Query(value = """
            SELECT t.id as id,t.name as name,count(rs.id) as resourceCount FROM public.technology t
            left join resource_skill rs on rs.technology_id = t.id and rs.is_active=true
            where t.is_active=true and t.name ILIKE :name
            group by rs.technology_id,t.id
            """,countQuery = """
            SELECT count(rs.id) FROM public.technology t
            left join resource_skill rs on rs.technology_id = t.id and rs.is_active=true
            where t.is_active=true and t.name ILIKE :name
            group by rs.technology_id,t.id
            """,nativeQuery = true)
    Page<TechnologyData>findByIsActiveTrueAndName(String name,Pageable pageable);

    @Query("select case when count(t.id) > 0 then true else false end\n" +
            "    from Technology t\n" +
            "    where t.isActive = true\n" +
            " and t.id !=?2     and lower(t.name) like lower(concat('%', ?1, '%'))")
    boolean existsByNameAndIsActiveTrueAndIdNot(String name,Long id);

    List<Technology> findByIsActiveTrueAndIdIn(List<Long> ids);
}
