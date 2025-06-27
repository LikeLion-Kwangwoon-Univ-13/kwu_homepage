package com.example.homepage.projectimage.converter;

import com.example.homepage.projectimage.dto.ProjectImageRequestDTO;
import com.example.homepage.projectimage.dto.ProjectImageResponseDTO;
import com.example.homepage.projectimage.entity.ProjectImage;
import com.example.homepage.projects.entity.Projects;

public class ProjectImageConverter {
    public static ProjectImage toEntity(ProjectImageRequestDTO dto, Projects project) {
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
