package com.example.erp.backend.repositories;

import com.example.erp.backend.entities.ClientContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientContactRep extends JpaRepository<ClientContact,Long> {
}
