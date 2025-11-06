package com.example.erp.backend.repositories;

import com.example.erp.backend.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRep extends JpaRepository<Client,Long> {
}
