package com.example.homepage.Member.converter;

import com.example.homepage.Member.entity.Generation;
import com.example.homepage.Member.dto.MemberCreateRequestDTO;
import com.example.homepage.Member.dto.MemberWithStacksResponseDTO;
import com.example.homepage.Member.entity.Member;
import com.example.homepage.Member.repository.MemberStacksRepository;

import java.util.List;
import java.util.stream.Collectors;

public class MembersConverter {
    public static Member toEntity(MemberCreateRequestDTO dto, Generation generation) {
        return Member.builder()
                .generation(generation)
                .profile(dto.getProfile())
                .name(dto.getName())
                .position(dto.getPosition())
                .part(dto.getPart())
                .github(dto.getGithub())
                .instagram(dto.getInstagram())
                .build();
    }

    public static MemberWithStacksResponseDTO toDto(Member member, List<String> stacks) {
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
