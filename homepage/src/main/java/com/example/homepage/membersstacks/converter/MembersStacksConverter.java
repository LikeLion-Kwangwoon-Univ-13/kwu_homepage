package com.example.homepage.membersstacks.converter;

import com.example.homepage.members.entity.Members;
import com.example.homepage.membersstacks.dto.MembersStacksRequestDTO;
import com.example.homepage.membersstacks.dto.MembersStacksResponseDTO;
import com.example.homepage.membersstacks.entity.MembersStacks;
import com.example.homepage.stacks.entity.Stacks;

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
