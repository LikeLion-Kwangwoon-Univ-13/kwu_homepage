package com.example.homepage.Member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberUpdateRequestDTO {
    private String profile;
    private String name;
    private int generation;
    private String position;
    private String part;
    private String github;
    private String instagram;
}