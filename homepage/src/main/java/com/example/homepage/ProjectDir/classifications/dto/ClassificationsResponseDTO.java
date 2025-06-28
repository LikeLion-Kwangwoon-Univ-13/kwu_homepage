package com.example.homepage.ProjectDir.classifications.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassificationsResponseDTO {
    private long id;
    private String category_name;
}
