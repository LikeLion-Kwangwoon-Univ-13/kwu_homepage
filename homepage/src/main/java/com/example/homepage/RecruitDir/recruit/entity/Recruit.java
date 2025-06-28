package com.example.homepage.RecruitDir.recruit.entity;

import com.example.homepage.RecruitDir.recruit.dto.RecruitRequestDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recruit {
    // 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // 구글폼 URL
    private String url;
    // 서류 모집
    private LocalDate documentDate;
    // 합격자 발표
    private LocalDate candidateDate;
    // 면접 날짜
    private LocalDate interviewDate;
    // 최종 합격
    private LocalDate acceptDate;
    // OT 날짜
    private LocalDate otDate;

    // 취소 여부
    // Integer -> boolean 변경
    private boolean isDeleted;

    public void update(RecruitRequestDTO dto) {
        this.url = dto.getUrl();
        this.documentDate = dto.getDocumentDate();
        this.candidateDate = dto.getCandidateDate();
        this.interviewDate = dto.getInterviewDate();
        this.acceptDate = dto.getAcceptDate();
        this.otDate = dto.getOtDate();
    }
}
