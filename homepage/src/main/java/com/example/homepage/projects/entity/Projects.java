package com.example.homepage.projects.entity;

import com.example.homepage.projectclassifications.entity.ProjectClassifications;
import com.example.homepage.projectimage.entity.ProjectImage;
import com.example.homepage.projectmembers.entity.ProjectMembers;
import com.example.homepage.projectstacks.entity.ProjectStacks;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String line_introduction;
    private String introduction;
    private LocalDate start_date;
    private LocalDate end_date;
    private int is_star;
    private int generation;
    private String part;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectImage> images;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectMembers> projectMembers;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectStacks> projectStacks;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectClassifications> projectClassifications;
}
