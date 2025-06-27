package com.example.homepage.recruit.service;

import com.example.homepage.recruit.converter.RecruitRequestDtoToRecruitConverter;
import com.example.homepage.recruit.converter.RecruitToRecruitResponseDtoConverter;
import com.example.homepage.recruit.dto.RecruitRequestDTO;
import com.example.homepage.recruit.dto.RecruitResponseDTO;
import com.example.homepage.recruit.entity.Recruit;
import com.example.homepage.recruit.repository.RecruitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecruitServiceImpl implements RecruitService {

    private final RecruitRepository recruitRepository;
    private final RecruitRequestDtoToRecruitConverter requestConverter;
    private final RecruitToRecruitResponseDtoConverter responseConverter;

    @Override
    public RecruitResponseDTO getRecruit() {
        Recruit recruit = recruitRepository.findById(1)
                .orElseThrow(() -> new RuntimeException("리쿠르트 오류 발생"));
        return responseConverter.convert(recruit);
    }

    @Override
    public void createRecruit(RecruitRequestDTO dto) {
        Recruit recruit = requestConverter.convert(dto);
        recruitRepository.save(recruit);
    }

    @Override
    public void updateRecruit(Integer id, RecruitRequestDTO dto) {
        Recruit recruit = recruitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("리쿠르트 오류 발생"));
        recruit.update(dto);
        recruitRepository.save(recruit);
    }
}
