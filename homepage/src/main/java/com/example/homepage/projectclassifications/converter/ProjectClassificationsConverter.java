package com.example.homepage.projectclassifications.converter;

import com.example.homepage.classifications.entity.Classifications;
import com.example.homepage.projectclassifications.dto.ProjectClassificationsRequestDTO;
import com.example.homepage.projectclassifications.dto.ProjectClassificationsResponseDTO;
import com.example.homepage.projectclassifications.entity.ProjectClassifications;
import com.example.homepage.projects.entity.Projects;

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
