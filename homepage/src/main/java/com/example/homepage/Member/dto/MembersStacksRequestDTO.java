package com.example.homepage.Member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MembersStacksRequestDTO {
    private long member_id;
    private long stack_id;
}
