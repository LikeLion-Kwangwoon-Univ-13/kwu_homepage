package com.example.homepage.ProjectDir.projects.entity;

import com.example.homepage.ProjectDir.projectclassifications.entity.ProjectClassifications;
import com.example.homepage.ProjectDir.projectimage.entity.ProjectImage;
import com.example.homepage.MemberDir.projectmembers.entity.ProjectMembers;
import com.example.homepage.ProjectDir.projects.dto.ProjectUpdateRequestDTO;
import com.example.homepage.ProjectDir.projectstacks.entity.ProjectStacks;
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

    public void updateFromDto(ProjectUpdateRequestDTO dto) {
        if (dto.getTitle() != null) this.title = dto.getTitle();
        if (dto.getLine_introduction() != null) this.line_introduction = dto.getLine_introduction();
        if (dto.getIntroduction() != null) this.introduction = dto.getIntroduction();
        if (dto.getPart() != null) this.part = dto.getPart();
        if (dto.getStart_Date() != null) this.start_date = dto.getStart_Date();
        if (dto.getEnd_Date() != null) this.end_date = dto.getEnd_Date();
    }

}
