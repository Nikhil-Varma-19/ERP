package com.example.erp.backend.repositories;

import com.example.erp.backend.entities.ErrorLogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorLoggerRep extends JpaRepository<ErrorLogger,Long> {
}
