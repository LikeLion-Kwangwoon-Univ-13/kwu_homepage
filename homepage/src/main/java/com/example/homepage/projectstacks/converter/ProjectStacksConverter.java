package com.example.homepage.projectstacks.converter;

import com.example.homepage.projects.entity.Projects;
import com.example.homepage.projectstacks.dto.ProjectStacksRequestDTO;
import com.example.homepage.projectstacks.dto.ProjectStacksResponseDTO;
import com.example.homepage.projectstacks.entity.ProjectStacks;
import com.example.homepage.stacks.entity.Stacks;

public class ProjectStacksConverter {
    public static ProjectStacks toEntity(ProjectStacksRequestDTO dto, Projects project, Stacks stack) {
        return ProjectStacks.builder()
                .project(project)
                .stack(stack)
                .build();
    }

    public static ProjectStacksResponseDTO toDto(ProjectStacks entity) {
        return ProjectStacksResponseDTO.builder()
                .id(entity.getId())
                .project_id(entity.getProject().getId())
                .stack_id(entity.getStack().getId())
                .build();
    }
}
