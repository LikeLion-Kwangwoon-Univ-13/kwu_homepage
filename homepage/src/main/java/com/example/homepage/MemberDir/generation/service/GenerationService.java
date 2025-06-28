package com.example.homepage.MemberDir.generation.service;

import com.example.homepage.MemberDir.generation.dto.GenerationRequestDTO;
import org.springframework.http.ResponseEntity;

public interface GenerationService {
    ResponseEntity<?> createGeneration(GenerationRequestDTO dto);
    ResponseEntity<?> deleteGeneration(long id);
}
