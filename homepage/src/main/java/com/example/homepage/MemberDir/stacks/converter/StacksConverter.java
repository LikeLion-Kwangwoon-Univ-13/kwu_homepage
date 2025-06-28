package com.example.homepage.MemberDir.stacks.converter;

import com.example.homepage.MemberDir.stacks.dto.StackRequestDTO;
import com.example.homepage.MemberDir.stacks.dto.StackResponseDTO;
import com.example.homepage.MemberDir.stacks.entity.Stacks;

public class StacksConverter {
    public static Stacks toEntity(StackRequestDTO dto) {
        return Stacks.builder()
                .name(dto.getName())
                .build();
    }

    public static StackResponseDTO toDto(Stacks entity) {
        return StackResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
