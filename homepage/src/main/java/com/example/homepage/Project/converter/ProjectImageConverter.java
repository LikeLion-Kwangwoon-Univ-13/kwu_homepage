package com.example.homepage.Project.converter;

import com.example.homepage.Project.dto.ProjectImageRequestDTO;
import com.example.homepage.Project.dto.ProjectImageResponseDTO;
import com.example.homepage.Project.entity.ProjectImage;
import com.example.homepage.Project.entity.Project;

public class ProjectImageConverter {
    public static ProjectImage toEntity(ProjectImageRequestDTO dto, Project project) {
        return ProjectImage.builder()
                .url(dto.getUrl())
                .orderIndex(dto.getOrderIndex())
                .project(project)
                .build();
    }

    public static ProjectImageResponseDTO toDto(ProjectImage entity) {
        return ProjectImageResponseDTO.builder()
                .id(entity.getId())
                .url(entity.getUrl())
                .orderIndex(entity.getOrderIndex())
                .project_id(entity.getProject().getId())
                .build();
    }
}
