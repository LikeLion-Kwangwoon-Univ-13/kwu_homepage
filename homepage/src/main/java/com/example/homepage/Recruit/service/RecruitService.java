package com.example.homepage.Recruit.service;

import com.example.homepage.Recruit.dto.RecruitRequestDTO;
import com.example.homepage.Recruit.dto.RecruitResponseDTO;

public interface RecruitService {
    RecruitResponseDTO getRecruit();             // GET /api/recruit
    void createRecruit(RecruitRequestDTO dto);   // POST
    void updateRecruit(Long id, RecruitRequestDTO dto); // PATCH
}
