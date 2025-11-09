package com.example.erp.backend.repositories;

import com.example.erp.backend.entities.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileUploadRes extends JpaRepository<FileUpload,Long> {
    Optional<FileUpload> findByIdAndIsActiveTrue(Long id);
}
