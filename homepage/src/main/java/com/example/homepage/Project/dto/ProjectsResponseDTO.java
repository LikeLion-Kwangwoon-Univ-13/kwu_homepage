package com.example.homepage.Project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectsResponseDTO {
    private long id;
    private String title;
    private String line_introduction;
    private String introduction;
    private LocalDate start_Date;
    private LocalDate end_Date;
    private int is_Star;
    private int generation;
    private String part;
}
