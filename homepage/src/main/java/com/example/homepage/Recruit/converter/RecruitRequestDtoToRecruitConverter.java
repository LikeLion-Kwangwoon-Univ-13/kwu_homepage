package com.example.homepage.Recruit.converter;

import com.example.homepage.Recruit.dto.RecruitRequestDTO;
import com.example.homepage.Recruit.entity.Recruit;
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
                .isDeleted(false)  // 기본값
                .build();
    }
}
