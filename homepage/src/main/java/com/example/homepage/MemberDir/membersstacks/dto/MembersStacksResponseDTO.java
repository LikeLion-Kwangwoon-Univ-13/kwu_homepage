package com.example.homepage.MemberDir.membersstacks.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MembersStacksResponseDTO {
    private long id;
    private long member_id;
    private long stack_id;
}
