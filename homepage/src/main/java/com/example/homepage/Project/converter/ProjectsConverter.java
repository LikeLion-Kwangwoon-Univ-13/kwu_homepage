package com.example.homepage.Project.converter;

import com.example.homepage.Member.entity.Member;
import com.example.homepage.Project.dto.*;
import com.example.homepage.Project.entity.Project;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProjectsConverter {
    public static Project toEntity(ProjectsRequestDTO dto) {
        return Project.builder()
                .title(dto.getTitle())
                .lineIntroduction(dto.getLine_introduction())
                .introduction(dto.getIntroduction())
                .startDate(dto.getStart_Date())
                .endDate(dto.getEnd_Date())
                .isStar(dto.getIs_Star())
                .generation(dto.getGeneration())
                .part(dto.getPart())
                .build();
    }

    public static ProjectsResponseDTO toDto(Project entity) {
        return ProjectsResponseDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .line_introduction(entity.getLineIntroduction())
                .introduction(entity.getIntroduction())
                .start_Date(entity.getStartDate())
                .end_Date(entity.getEndDate())
                .is_Star(entity.getIsStar())
                .generation(entity.getGeneration())
                .part(entity.getPart())
                .build();
    }

    public static ProjectHomeResponseDTO toHomeDTO(Project project, String imageUrl) {
        return ProjectHomeResponseDTO.builder()
                .title(project.getTitle())
                .line_introduction(project.getLineIntroduction())
                .url(imageUrl)
                .build();
    }

    public static ProjectHistoryResponseDTO toHistoryDTO(Project project, String imageUrl) {
        return ProjectHistoryResponseDTO.builder()
                .id(project.getId())
                .title(project.getTitle())
                .line_introduction(project.getLineIntroduction())
                .part(project.getPart())
                .url(imageUrl)
                .heart(project.getIsStar() != 0 ? project.getIsStar() : 0)
                .build();
    }

    public static ProjectDetailResponseDTO toDetailDTO(Project project,
                                                       List<Member> projectMembers,
                                                       List<String> projectStacks,
                                                       String imageUrl) {
        // 1) 멤버 정보 맵핑 (이름 → 파트)
        Map<String, String> members = projectMembers.stream()
                .collect(Collectors.toMap(
                        Member::getName,    // pm -> pm.getName()  → Member::getName
                        Member::getPart
                ));

        return ProjectDetailResponseDTO.builder()
                .id(project.getId())
                .title(project.getTitle())
                .line_introduction(project.getLineIntroduction())
                .url(imageUrl)
                .introduction(project.getIntroduction())
                .project_member(members)
                .stacks(projectStacks)
                .part(project.getPart())
                .start_Date(project.getStartDate())
                .end_Date(project.getEndDate())
                .build();
    }

    public static Project toEntity(ProjectCreateRequestDTO dto) {
        return Project.builder()
                .title(dto.getTitle())
                .lineIntroduction(dto.getLine_introduction())
                .introduction(dto.getIntroduction())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .isStar(0)
                .generation(dto.getGeneration())
                .part(dto.getPart())
                .build();
    }

    public static Project updateEntityFromDTO(Project project, ProjectUpdateRequestDTO dto) {
        return Project.builder()
                .id(project.getId())
                .title(dto.getTitle() != null ? dto.getTitle() : project.getTitle())
                .lineIntroduction(dto.getLine_introduction() != null ? dto.getLine_introduction() : project.getLineIntroduction())
                .introduction(dto.getIntroduction() != null ? dto.getIntroduction() : project.getIntroduction())
                .part(dto.getPart() != null ? dto.getPart() : project.getPart())
                .startDate(dto.getStart_Date() != null ? dto.getStart_Date() : project.getStartDate())
                .endDate(dto.getEnd_Date() != null ? dto.getEnd_Date() : project.getEndDate())
                .generation(project.getGeneration())
                .isStar(project.getIsStar())
                .build();
    }
}
