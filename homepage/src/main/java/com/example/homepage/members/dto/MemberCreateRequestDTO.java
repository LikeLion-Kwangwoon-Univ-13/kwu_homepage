package com.example.homepage.members.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberCreateRequestDTO {
    private String profile;
    private String name;
    private int generation;
    private String position;
    private String part;
    private String github;
    private String instagram;
    private List<String> stacks;
}
