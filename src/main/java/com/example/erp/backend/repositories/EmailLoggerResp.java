package com.example.erp.backend.repositories;

import com.example.erp.backend.entities.EmailLogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailLoggerResp extends JpaRepository<EmailLogger,Long> {
}
