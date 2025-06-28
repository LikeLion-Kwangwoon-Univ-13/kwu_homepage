package com.example.homepage.MemberDir.projectmembers.converter;

import com.example.homepage.MemberDir.members.entity.Members;
import com.example.homepage.MemberDir.projectmembers.dto.ProjectMembersRequestDTO;
import com.example.homepage.MemberDir.projectmembers.dto.ProjectMembersResponseDTO;
import com.example.homepage.MemberDir.projectmembers.entity.ProjectMembers;
import com.example.homepage.ProjectDir.projects.entity.Projects;

public class ProjectMembersConverter {
    public static ProjectMembers toEntity(ProjectMembersRequestDTO dto, Projects project, Members member) {
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
