package com.example.erp.backend.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "error_logger")
@EntityListeners(AuditingEntityListener.class)
@Entity
@Data
public class ErrorLogger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    private String method;

    private String body;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Column(name = "trace_id",nullable = false)
    private UUID traceId;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(name="created_by")
    private Long createdBy;

}
