package com.example.erp.backend.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.Where;

@Table(name = "resource_skill")
@Entity
//@Where(clause = "is_active = true")
public class ResourceSkill extends  DBCommon{


    @ManyToOne
    @JoinColumn(name = "resource_id")
    @JsonBackReference
    private Resource resource;

    @ManyToOne
    @JoinColumn(name = "technology_id")
    @JsonBackReference(value = "tech-skill")
   private Technology technology;


}
