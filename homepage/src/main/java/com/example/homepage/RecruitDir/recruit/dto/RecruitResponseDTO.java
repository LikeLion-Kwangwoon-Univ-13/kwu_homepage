package com.example.homepage.RecruitDir.recruit.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecruitResponseDTO {
    private Long id;
    private String url;
    private LocalDate documentDate;
    private LocalDate candidateDate;
    private LocalDate interviewDate;
    private LocalDate acceptDate;
    private LocalDate otDate;
}
