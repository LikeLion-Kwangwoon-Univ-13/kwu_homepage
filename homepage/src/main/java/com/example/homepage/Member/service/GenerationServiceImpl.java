package com.example.homepage.Member.service;

import com.example.homepage.Member.dto.GenerationRequestDTO;
import com.example.homepage.Member.repository.GenerationRepository;
import com.example.homepage.Member.entity.Generation;
import com.example.homepage.Member.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class GenerationServiceImpl implements GenerationService {
    private final GenerationRepository generationRepository;
    private final MemberRepository memberRepository;

    @Override
    public ResponseEntity<?> createGeneration(GenerationRequestDTO dto) {
        if (generationRepository.existsByGeneration(dto.getGeneration())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "기수가 이미 존재합니다."));
        }

        Generation young = Generation.builder()
                .generation(dto.getGeneration())
                .build();

        Generation saved = generationRepository.save(young);

        return ResponseEntity.ok().body("기수가 등록되었습니다.");
    }

    @Override
    public ResponseEntity<?> deleteGeneration(long id) {
        Generation generation = generationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 기수를 찾을 수 없습니다."));

        // Member 삭제
        if (memberRepository.existsByGenerationId(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "멤버가 있어 삭제할 수 없습니다."));
        }

        generationRepository.delete(generation);

        return ResponseEntity.ok(Map.of("msg", "기수가 삭제되었습니다."));
    }
}
