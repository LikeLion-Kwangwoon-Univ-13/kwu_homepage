package com.example.homepage.MemberDir.membersstacks.converter;

import com.example.homepage.MemberDir.members.entity.Members;
import com.example.homepage.MemberDir.membersstacks.dto.MembersStacksRequestDTO;
import com.example.homepage.MemberDir.membersstacks.dto.MembersStacksResponseDTO;
import com.example.homepage.MemberDir.membersstacks.entity.MembersStacks;
import com.example.homepage.MemberDir.stacks.entity.Stacks;

public class MembersStacksConverter {
    public static MembersStacks toEntity(MembersStacksRequestDTO dto, Members member, Stacks stack) {
        return MembersStacks.builder()
                .member(member)
                .stack(stack)
                .build();
    }

    public static MembersStacksResponseDTO toDto(MembersStacks entity) {
        return MembersStacksResponseDTO.builder()
                .id(entity.getId())
                .member_id(entity.getMember().getId())
                .stack_id(entity.getStack().getId())
                .build();
    }
}
