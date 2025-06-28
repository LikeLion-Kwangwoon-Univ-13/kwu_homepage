package com.example.homepage.projects.converter;

import com.example.homepage.projects.dto.*;
import com.example.homepage.projects.entity.Projects;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProjectsConverter {
    public static Projects toEntity(ProjectsRequestDTO dto) {
        return Projects.builder()
                .title(dto.getTitle())
                .line_introduction(dto.getLine_introduction())
                .introduction(dto.getIntroduction())
                .start_date(dto.getStart_Date())
                .end_date(dto.getEnd_Date())
                .is_star(dto.getIs_Star())
                .generation(dto.getGeneration())
                .part(dto.getPart())
                .build();
    }

    public static ProjectsResponseDTO toDto(Projects entity) {
        return ProjectsResponseDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .line_introduction(entity.getLine_introduction())
                .introduction(entity.getIntroduction())
                .start_Date(entity.getStart_date())
                .end_Date(entity.getEnd_date())
                .is_Star(entity.getIs_star())
                .generation(entity.getGeneration())
                .part(entity.getPart())
                .build();
    }

    public static ProjectHomeResponseDTO toHomeDTO(Projects project) {
        return ProjectHomeResponseDTO.builder()
                .title(project.getTitle())
                .line_introduction(project.getLine_introduction())
                .url(
                        project.getImages() != null && !project.getImages().isEmpty()
                                ? project.getImages().get(0).getUrl()
                                : ""
                )
                .build();
    }

    public static ProjectHistoryResponseDTO toHistoryDTO(Projects project) {
        return ProjectHistoryResponseDTO.builder()
                .id(project.getId())
                .title(project.getTitle())
                .line_introduction(project.getLine_introduction())
                .part(project.getPart())
                .url(
                        project.getImages() != null && !project.getImages().isEmpty()
                                ? project.getImages().get(0).getUrl()
                                : ""
                )
                .heart(project.getIs_star() != 0 ? project.getIs_star() : 0)
                .build();
    }

    public static ProjectDetailResponseDTO toDetailDTO(Projects project) {
        Map<String, String> members = project.getProjectMembers().stream()
                .collect(Collectors.toMap(
                        m -> m.getMember().getName(),
                        m -> m.getMember().getPart(),
                        (v1, v2) -> v1
                ));

        List<String> stacks = project.getProjectStacks().stream()
                .map(ps -> ps.getStack().getName())
                .collect(Collectors.toList());

        return ProjectDetailResponseDTO.builder()
                .id(project.getId())
                .title(project.getTitle())
                .line_introduction(project.getLine_introduction())
                .url(project.getImages() != null && !project.getImages().isEmpty() ? project.getImages().get(0).getUrl() : "")
                .introduction(project.getIntroduction())
                .project_member(members)
                .stacks(stacks)
                .part(project.getPart())
                .start_Date(project.getStart_date())
                .end_Date(project.getEnd_date())
                .build();
    }

    public static Projects toEntity(ProjectCreateRequestDTO dto) {
        return Projects.builder()
                .title(dto.getTitle())
                .line_introduction(dto.getLine_introduction())
                .introduction(dto.getIntroduction())
                .start_date(dto.getStart_Date())
                .end_date(dto.getEnd_Date())
                .is_star(0)
                .generation(dto.getGeneration())
                .part(dto.getPart())
                .build();
    }

    public static Projects updateEntityFromDTO(Projects project, ProjectUpdateRequestDTO dto) {
        return Projects.builder()
                .id(project.getId())
                .title(dto.getTitle() != null ? dto.getTitle() : project.getTitle())
                .line_introduction(dto.getLine_introduction() != null ? dto.getLine_introduction() : project.getLine_introduction())
                .introduction(dto.getIntroduction() != null ? dto.getIntroduction() : project.getIntroduction())
                .part(dto.getPart() != null ? dto.getPart() : project.getPart())
                .start_date(dto.getStart_Date() != null ? dto.getStart_Date() : project.getStart_date())
                .end_date(dto.getEnd_Date() != null ? dto.getEnd_Date() : project.getEnd_date())
                .generation(project.getGeneration())
                .is_star(project.getIs_star())
                .projectMembers(project.getProjectMembers())
                .projectStacks(project.getProjectStacks())
                .images(project.getImages())
                .build();
    }
}
