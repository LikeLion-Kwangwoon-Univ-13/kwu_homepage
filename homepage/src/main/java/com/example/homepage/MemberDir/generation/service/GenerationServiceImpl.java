package com.example.homepage.MemberDir.generation.service;

import com.example.homepage.MemberDir.generation.dto.GenerationRequestDTO;
import com.example.homepage.MemberDir.generation.repository.GenerationRepository;
import com.example.homepage.MemberDir.generation.entity.Generation;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenerationServiceImpl implements GenerationService {
    private final GenerationRepository generationRepository;

    @Override
    public ResponseEntity<?> createGeneration(GenerationRequestDTO dto) {
        Optional<Generation> old = generationRepository.findByGeneration(dto.getGeneration());
        if (old.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "기수가 이미 존재합니다."));
        }

        Generation young = Generation.builder()
                .generation(dto.getGeneration())
                .build();

        generationRepository.save(young);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("msg", "기수가 추가되었습니다."));
    }

    @Override
    public ResponseEntity<?> deleteGeneration(long id) {
        Generation generation = generationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 기수를 찾을 수 없습니다."));

        generationRepository.delete(generation);
        return ResponseEntity.ok(Map.of("msg", "기수가 삭제되었습니다."));
    }
}
