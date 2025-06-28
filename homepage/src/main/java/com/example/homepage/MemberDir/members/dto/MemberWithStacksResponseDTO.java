package com.example.homepage.MemberDir.members.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberWithStacksResponseDTO {
    private long id;
    private String profile;
    private String name;
    private int generation;
    private String position;
    private String part;
    private String github;
    private String instagram;
    private List<String> stacks;
}
