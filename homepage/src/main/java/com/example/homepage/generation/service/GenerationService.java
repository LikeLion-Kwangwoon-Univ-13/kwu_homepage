package com.example.homepage.generation.service;

import com.example.homepage.generation.dto.GenerationRequestDTO;
import org.springframework.http.ResponseEntity;

public interface GenerationService {
    ResponseEntity<?> createGeneration(GenerationRequestDTO dto);
    ResponseEntity<?> deleteGeneration(long id);
}
