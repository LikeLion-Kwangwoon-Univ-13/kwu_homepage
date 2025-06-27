package com.example.homepage.projectstacks.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectStacksRequestDTO {
    private long project_id;
    private long stack_id;
}
