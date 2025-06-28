package com.example.homepage.ProjectDir.projectstacks.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectStacksResponseDTO {
    private long id;
    private long project_id;
    private long stack_id;
}
