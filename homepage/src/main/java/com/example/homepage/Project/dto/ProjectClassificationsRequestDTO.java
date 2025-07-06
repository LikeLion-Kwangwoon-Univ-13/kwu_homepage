package com.example.homepage.Project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectClassificationsRequestDTO {
    private long project_id;
    private long classification_id;
}
