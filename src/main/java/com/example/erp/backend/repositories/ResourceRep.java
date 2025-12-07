package com.example.erp.backend.repositories;

import com.example.erp.backend.entities.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResourceRep extends JpaRepository<Resource, Long> {
    boolean existsByMobileNumber(String mobileNumber);

    boolean existsByPersonalEmail(String personalEmail);

    boolean existsByCompanyEmail(String companyEmail);

    Optional<Resource> findByIdAndIsActiveTrue(Long id);

    @Modifying
    @Query("UPDATE Resource r set r.isActive = false WHERE r.isActive = true and r.id = :id ")
    int deleteResourceSoft(Long id);


    @Query("SELECT  r FROM Resource r " +
            "LEFT JOIN FETCH r.vendor v " +
            "LEFT JOIN FETCH r.skills s " +
            "LEFT JOIN FETCH s.technology t " +
            "WHERE r.id = :id AND r.isActive = true")
    Optional<Resource> findByIdWithVendorAndSkills(Long id);


    @Query("SELECT DISTINCT r FROM Resource r " +
            "LEFT JOIN FETCH r.skills s " +
            "LEFT JOIN FETCH s.technology t " +
            "LEFT JOIN FETCH r.vendor v " +
            "WHERE r.isActive = true AND s.isActive = true AND"+
            "( LOWER(r.firstName) LIKE LOWER(:search) OR "+
            "LOWER(r.lastName) LIKE LOWER(:search) OR "+
            "LOWER(r.companyEmail) LIKE LOWER(:search) OR "+
            "LOWER(r.mobileNumber) LIKE LOWER(:search) OR "+
            "LOWER(r.personalEmail) LIKE LOWER(:search) OR "+
            "LOWER(t.name) LIKE LOWER(:search) OR "+
            "LOWER(v.name) LIKE LOWER(:search) OR "+
            "LOWER(r.resumeType) LIKE LOWER(:search) )")
    Page<Resource> findAllWithTechnologiesAndVendor(String search,Pageable pageable);
}
