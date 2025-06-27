package com.example.homepage.recruit.service;

import com.example.homepage.recruit.dto.RecruitRequestDTO;
import com.example.homepage.recruit.dto.RecruitResponseDTO;

public interface RecruitService {
    RecruitResponseDTO getRecruit();             // GET /api/recruit
    void createRecruit(RecruitRequestDTO dto);   // POST
    void updateRecruit(Integer id, RecruitRequestDTO dto); // PATCH
}
