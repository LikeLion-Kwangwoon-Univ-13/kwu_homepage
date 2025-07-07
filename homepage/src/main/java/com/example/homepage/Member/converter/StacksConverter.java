package com.example.homepage.Member.converter;

import com.example.homepage.Member.dto.StackRequestDTO;
import com.example.homepage.Member.dto.StackResponseDTO;
import com.example.homepage.Member.entity.Stack;

public class StacksConverter {
    public static Stack toEntity(StackRequestDTO dto) {
        return Stack.builder()
                .name(dto.getName())
                .build();
    }

    public static StackResponseDTO toDto(Stack entity) {
        return StackResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
