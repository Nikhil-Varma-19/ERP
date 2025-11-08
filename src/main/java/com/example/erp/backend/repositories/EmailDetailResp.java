package com.example.erp.backend.repositories;

import com.example.erp.backend.entities.EmailDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailDetailResp extends JpaRepository<EmailDetail,Long> {
    @Query("Select e from EmailDetail e where e.isActive = true")
    Optional<EmailDetail> getEmailData();
}
