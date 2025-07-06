package com.example.homepage.Project.converter;

import com.example.homepage.Project.entity.Projects;
import com.example.homepage.Project.dto.ProjectStacksRequestDTO;
import com.example.homepage.Project.dto.ProjectStacksResponseDTO;
import com.example.homepage.Project.entity.ProjectStacks;
import com.example.homepage.Member.entity.Stacks;

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
