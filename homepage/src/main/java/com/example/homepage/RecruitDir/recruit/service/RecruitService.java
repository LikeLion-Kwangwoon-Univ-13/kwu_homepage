package com.example.homepage.RecruitDir.recruit.service;

import com.example.homepage.RecruitDir.recruit.dto.RecruitRequestDTO;
import com.example.homepage.RecruitDir.recruit.dto.RecruitResponseDTO;

public interface RecruitService {
    RecruitResponseDTO getRecruit();             // GET /api/recruit
    void createRecruit(RecruitRequestDTO dto);   // POST
    void updateRecruit(Long id, RecruitRequestDTO dto); // PATCH
}
