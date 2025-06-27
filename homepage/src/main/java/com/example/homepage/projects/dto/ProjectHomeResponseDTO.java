package com.example.homepage.projects.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectHomeResponseDTO {
    private String title;
    private String line_introduction;
    private String url;
}
