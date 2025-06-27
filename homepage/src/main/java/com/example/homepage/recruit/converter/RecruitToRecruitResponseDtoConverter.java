package com.example.homepage.recruit.converter;

import com.example.homepage.recruit.dto.RecruitResponseDTO;
import com.example.homepage.recruit.entity.Recruit;
import org.springframework.stereotype.Component;

@Component
public class RecruitToRecruitResponseDtoConverter {
    public RecruitResponseDTO convert(Recruit recruit) {
        return RecruitResponseDTO.builder()
                .id(recruit.getId())
                .url(recruit.getUrl())
                .documentDate(recruit.getDocumentDate())
                .candidateDate(recruit.getCandidateDate())
                .interviewDate(recruit.getInterviewDate())
                .acceptDate(recruit.getAcceptDate())
                .otDate(recruit.getOtDate())
                .build();
    }
}
