package com.example.homepage.Member.converter;

import com.example.homepage.Member.entity.Member;
import com.example.homepage.Member.dto.MembersStacksRequestDTO;
import com.example.homepage.Member.dto.MembersStacksResponseDTO;
import com.example.homepage.Member.entity.MemberStacks;
import com.example.homepage.Member.entity.Stack;

public class MembersStacksConverter {
    public static MemberStacks toEntity(MembersStacksRequestDTO dto, Member member, Stack stack) {
        return MemberStacks.builder()
                .member(member)
                .stack(stack)
                .build();
    }

    public static MembersStacksResponseDTO toDto(MemberStacks entity) {
        return MembersStacksResponseDTO.builder()
                .id(entity.getId())
                .member_id(entity.getMember().getId())
                .stack_id(entity.getStack().getId())
                .build();
    }
}
