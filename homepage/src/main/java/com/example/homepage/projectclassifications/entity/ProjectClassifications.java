package com.example.homepage.projectclassifications.entity;

import com.example.homepage.classifications.entity.Classifications;
import com.example.homepage.projects.entity.Projects;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectClassifications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Projects project;

    @ManyToOne
    @JoinColumn(name = "classification_id")
    private Classifications classification;
}
