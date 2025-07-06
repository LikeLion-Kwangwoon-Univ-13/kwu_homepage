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
public class ProjectUpdateRequestDTO {
    private String title;
    private String line_introduction;
    private String introduction;
    private String part;
    private LocalDate start_Date;
    private LocalDate end_Date;
}
