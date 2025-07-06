package com.example.homepage.Recruit.converter;

import com.example.homepage.Recruit.dto.RecruitResponseDTO;
import com.example.homepage.Recruit.entity.Recruit;
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
