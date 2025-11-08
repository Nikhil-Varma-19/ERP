package com.example.erp.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;

@Table(name = "email_templates")
@Entity
@Getter
public class EmailTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isActive=Boolean.TRUE;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String subject;

    private String event;
}
