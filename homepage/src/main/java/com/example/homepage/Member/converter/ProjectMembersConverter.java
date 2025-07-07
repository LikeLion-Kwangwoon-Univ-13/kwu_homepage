package com.example.homepage.Member.converter;

import com.example.homepage.Member.entity.Member;
import com.example.homepage.Member.dto.ProjectMembersRequestDTO;
import com.example.homepage.Member.dto.ProjectMembersResponseDTO;
import com.example.homepage.Member.entity.ProjectMembers;
import com.example.homepage.Project.entity.Projects;

public class ProjectMembersConverter {
    public static ProjectMembers toEntity(ProjectMembersRequestDTO dto, Projects project, Member member) {
        return ProjectMembers.builder()
                .project(project)
                .member(member)
                .build();
    }

    public static ProjectMembersResponseDTO toDto(ProjectMembers entity) {
        return ProjectMembersResponseDTO.builder()
                .id(entity.getId())
                .project_id(entity.getProject().getId())
                .member_id(entity.getMember().getId())
                .build();
    }
}
