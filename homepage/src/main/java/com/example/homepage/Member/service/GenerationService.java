package com.example.homepage.Member.service;

import com.example.homepage.Member.dto.GenerationRequestDTO;
import org.springframework.http.ResponseEntity;

public interface GenerationService {
    ResponseEntity<?> createGeneration(GenerationRequestDTO dto);
    ResponseEntity<?> deleteGeneration(long id);
}
