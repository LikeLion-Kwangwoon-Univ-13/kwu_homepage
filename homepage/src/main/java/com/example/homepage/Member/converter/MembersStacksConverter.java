package com.example.homepage.Member.converter;

import com.example.homepage.Member.entity.Members;
import com.example.homepage.Member.dto.MembersStacksRequestDTO;
import com.example.homepage.Member.dto.MembersStacksResponseDTO;
import com.example.homepage.Member.entity.MembersStacks;
import com.example.homepage.Member.entity.Stacks;

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
