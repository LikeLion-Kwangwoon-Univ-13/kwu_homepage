package com.example.homepage.Member.controller;

import com.example.homepage.Member.dto.GenerationRequestDTO;
import com.example.homepage.Member.service.GenerationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/manage/generation")
@RequiredArgsConstructor
public class GenerationController {
    private final GenerationService generationService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createGeneration(
            @RequestBody GenerationRequestDTO dto
    ){
        return generationService.createGeneration(dto);
    }

    // generation 삭제
    @DeleteMapping(value = "/{id}/delete")
    public ResponseEntity<?> deleteGeneration(
            @PathVariable("id") long id
    ){
        return generationService.deleteGeneration(id);
    }
}
