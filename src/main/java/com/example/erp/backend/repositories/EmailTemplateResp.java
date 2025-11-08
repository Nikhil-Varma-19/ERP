package com.example.erp.backend.repositories;

import com.example.erp.backend.entities.EmailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailTemplateResp extends JpaRepository<EmailTemplate,Long> {
    Optional<EmailTemplate> findByEventAndIsActiveTrue(String event);
}
