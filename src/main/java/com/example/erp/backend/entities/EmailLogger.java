package com.example.erp.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Table(name = "email_logger")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailLogger extends DBCommon {
    private String toMail;
    private String subject;
    private String others;
    @Column(columnDefinition = "TEXT")
    private String body;
    @Column(columnDefinition = "TEXT")
    private String error;
}
