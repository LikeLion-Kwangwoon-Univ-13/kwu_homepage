package com.example.homepage.projectimage.entity;

import com.example.homepage.projects.entity.Projects;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProjectImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String url;
    private int orderIndex;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Projects project;
}
