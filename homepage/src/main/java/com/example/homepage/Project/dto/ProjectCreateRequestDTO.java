package com.example.homepage.Project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectCreateRequestDTO {
    private String title;
    private String line_introduction;
    private String url;
    private String introduction;
    private List<String> projectMember;
    private List<String> stacks;
    private String part;
    private int generation;
    private LocalDate startDate;
    private LocalDate endDate;
}
