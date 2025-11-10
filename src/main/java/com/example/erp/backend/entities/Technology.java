package com.example.erp.backend.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Table(name="technology")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Technology extends  DBCommon{

    @Column
    private String name;

    @OneToMany(mappedBy = "technology")
    @JsonManagedReference(value = "tech-skill")
    private List<ResourceSkill> resources;

}
