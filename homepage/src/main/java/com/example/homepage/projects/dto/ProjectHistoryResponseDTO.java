package com.example.homepage.projects.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectHistoryResponseDTO {
    private long id;
    private String title;
    private String line_introduction;
    private String part;
    private String url;
    private int heart;
}
