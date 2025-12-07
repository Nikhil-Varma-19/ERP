package com.example.erp.backend.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "resource_skill")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResourceSkill extends  DBCommon{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resource_id")
//    @JsonBackReference
    private Resource resource;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "technology_id")
//    @JsonBackReference(value = "tech-skill")
   private Technology technology;


}
