package com.example.homepage.MemberDir.projectmembers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectMembersRequestDTO {
    private long project_id;
    private long member_id;
}
