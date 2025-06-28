package com.example.homepage.ProjectDir.projectclassifications.converter;

import com.example.homepage.ProjectDir.projectclassifications.dto.ProjectClassificationsRequestDTO;
import com.example.homepage.ProjectDir.projectclassifications.dto.ProjectClassificationsResponseDTO;
import com.example.homepage.ProjectDir.projectclassifications.entity.ProjectClassifications;
import com.example.homepage.ProjectDir.classifications.entity.Classifications;
import com.example.homepage.ProjectDir.projects.entity.Projects;

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
