package com.example.erp.backend.repositories;

import com.example.erp.backend.entities.ApiLogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiLoggerRep extends JpaRepository<ApiLogger,Long> {
}
