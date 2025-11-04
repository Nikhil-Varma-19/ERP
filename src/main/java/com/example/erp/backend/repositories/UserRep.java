package com.example.erp.backend.repositories;

import com.example.erp.backend.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRep extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);

    Page<User> findByIsActiveTrueAndNameContainingIgnoreCaseOrIsActiveTrueAndEmailContainingIgnoreCase(String name, String email, Pageable pageable);

    Page<User> findByIsActiveTrue(Pageable pageable);

    Optional<User> findByEmailAndIsActiveTrue(String email);

    Optional<User> findByIdAndIsActiveTrue(Long id);
}
