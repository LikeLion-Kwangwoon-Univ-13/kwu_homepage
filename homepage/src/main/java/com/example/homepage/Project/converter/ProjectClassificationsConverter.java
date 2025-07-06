package com.example.homepage.Project.converter;

import com.example.homepage.Project.dto.ProjectClassificationsRequestDTO;
import com.example.homepage.Project.dto.ProjectClassificationsResponseDTO;
import com.example.homepage.Project.entity.ProjectClassifications;
import com.example.homepage.Project.entity.Classifications;
import com.example.homepage.Project.entity.Projects;

public class ProjectClassificationsConverter {
    public static ProjectClassifications toEntity(ProjectClassificationsRequestDTO dto, Projects project, Classifications classification) {
        return ProjectClassifications.builder()
                .project(project)
                .classification(classification)
                .build();
    }

    public static ProjectClassificationsResponseDTO toDto(ProjectClassifications entity) {
        return ProjectClassificationsResponseDTO.builder()
                .id(entity.getId())
                .project_id(entity.getProject().getId())
                .classification_id(entity.getClassification().getId())
                .build();
    }
}
