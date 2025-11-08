package com.example.erp.backend.repositories;

import com.example.erp.backend.entities.OTPAlerts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OTPAlertResp extends JpaRepository<OTPAlerts,Long> {

}
