package com.example.homepage.projects.dto;

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
public class ProjectDetailResponseDTO {
    private long id;
    private String title;
    private String line_introduction;
    private String url;
    private String introduction;
    private Map<String, String> project_member;
    private List<String> stacks;
    private String part;
    private LocalDate start_Date;
    private LocalDate end_Date;
}
