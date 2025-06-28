package com.example.homepage.RecruitDir.recruit.service;

import com.example.homepage.RecruitDir.recruit.converter.RecruitRequestDtoToRecruitConverter;
import com.example.homepage.RecruitDir.recruit.converter.RecruitToRecruitResponseDtoConverter;
import com.example.homepage.RecruitDir.recruit.dto.RecruitRequestDTO;
import com.example.homepage.RecruitDir.recruit.dto.RecruitResponseDTO;
import com.example.homepage.RecruitDir.recruit.entity.Recruit;
import com.example.homepage.RecruitDir.recruit.repository.RecruitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RecruitServiceImpl implements RecruitService {

    private final RecruitRepository recruitRepository;
    private final RecruitRequestDtoToRecruitConverter requestConverter;
    private final RecruitToRecruitResponseDtoConverter responseConverter;

    @Override
    // 1번 id 값이 아닌 가장 작은 id 값으로 데이터 가져옴
    public RecruitResponseDTO getRecruit() {
        Recruit recruit = recruitRepository.findFirstByOrderByIdAsc()
                .orElseThrow(() -> new IllegalStateException("Recruit 데이터가 없습니다."));
        return responseConverter.convert(recruit);
    }

    @Override
    // 동시성 처리 문제 해결
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void createRecruit(RecruitRequestDTO dto) {
        // 처음만 생성 가능
        long totalCount = recruitRepository.count();
        if (totalCount != 0) {
            throw new IllegalStateException("Recruit 데이터는 하나만 생성할 수 있습니다.");
        }

        Recruit recruit = requestConverter.convert(dto);
        recruitRepository.save(recruit);
    }

    @Override
    public void updateRecruit(Integer id, RecruitRequestDTO dto) {
        Recruit recruit = recruitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 모집 일정이 존재하지 않습니다."));
        recruit.update(dto);
        recruitRepository.save(recruit);
    }
}
