package com.example.homepage.Project.converter;

import com.example.homepage.Project.entity.Project;
import com.example.homepage.Project.dto.ProjectStacksRequestDTO;
import com.example.homepage.Project.dto.ProjectStacksResponseDTO;
import com.example.homepage.Project.entity.ProjectStacks;
import com.example.homepage.Member.entity.Stack;

public class ProjectStacksConverter {
    public static ProjectStacks toEntity(ProjectStacksRequestDTO dto, Project project, Stack stack) {
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
