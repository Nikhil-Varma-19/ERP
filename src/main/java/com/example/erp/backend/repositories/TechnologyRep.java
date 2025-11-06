package com.example.erp.backend.repositories;

import com.example.erp.backend.entities.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyRep extends JpaRepository<Technology,Long> {
}
