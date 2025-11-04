package com.example.erp.backend.repositories;

import com.example.erp.backend.entities.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRep extends JpaRepository<Resource,Long> {
}
