package com.example.erp.backend.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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

    @OneToMany(mappedBy = "technology",fetch = FetchType.LAZY)
//    @JsonManagedReference(value = "tech-skill")
    @Builder.Default
    private List<ResourceSkill> resources=new ArrayList<>();

}
