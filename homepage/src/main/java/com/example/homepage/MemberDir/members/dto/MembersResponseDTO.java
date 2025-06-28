package com.example.homepage.MemberDir.members.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MembersResponseDTO {
    private long id;
    private int generation_id;
    private String profile;
    private String name;
    private String position;
    private String part;
    private String github;
    private String instagram;
    private LocalDate created_at;
}
