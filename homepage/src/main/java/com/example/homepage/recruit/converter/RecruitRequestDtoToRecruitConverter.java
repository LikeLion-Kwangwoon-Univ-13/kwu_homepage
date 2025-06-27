package com.example.homepage.recruit.converter;

import com.example.homepage.recruit.dto.RecruitRequestDTO;
import com.example.homepage.recruit.entity.Recruit;
import org.springframework.stereotype.Component;

@Component
public class RecruitRequestDtoToRecruitConverter {
    public Recruit convert(RecruitRequestDTO dto) {
        return Recruit.builder()
                .url(dto.getUrl())
                .documentDate(dto.getDocumentDate())
                .candidateDate(dto.getCandidateDate())
                .interviewDate(dto.getInterviewDate())
                .acceptDate(dto.getAcceptDate())
                .otDate(dto.getOtDate())
                .isDeleted(0)  // 기본값
                .build();
    }
}
