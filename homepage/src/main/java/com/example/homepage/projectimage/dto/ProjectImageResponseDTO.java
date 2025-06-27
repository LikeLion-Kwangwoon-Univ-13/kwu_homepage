package com.example.homepage.projectimage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectImageResponseDTO {
    private long id;
    private String url;
    private int orderIndex;
    private long project_id;
}
