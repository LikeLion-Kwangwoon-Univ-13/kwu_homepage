package com.example.homepage.MemberDir.members.converter;

import com.example.homepage.MemberDir.generation.entity.Generation;
import com.example.homepage.MemberDir.members.dto.MemberCreateRequestDTO;
import com.example.homepage.MemberDir.members.dto.MemberWithStacksResponseDTO;
import com.example.homepage.MemberDir.members.entity.Members;

import java.util.List;
import java.util.stream.Collectors;

public class MembersConverter {
    public static Members toEntity(MemberCreateRequestDTO dto, Generation generation) {
        return Members.builder()
                .generation(generation)
                .profile(dto.getProfile())
                .name(dto.getName())
                .position(dto.getPosition())
                .part(dto.getPart())
                .github(dto.getGithub())
                .instagram(dto.getInstagram())
                .build();
    }

    public static MemberWithStacksResponseDTO toDto(Members member) {
        List<String> stacks = member.getMembersStacks().stream()
                .map(ms -> ms.getStack().getName())
                .collect(Collectors.toList());

        return MemberWithStacksResponseDTO.builder()
                .id(member.getId())
                .profile(member.getProfile())
                .name(member.getName())
                .generation(member.getGeneration().getGeneration())
                .position(member.getPosition())
                .part(member.getPart())
                .github(member.getGithub())
                .instagram(member.getInstagram())
                .stacks(stacks)
                .build();
    }
}
