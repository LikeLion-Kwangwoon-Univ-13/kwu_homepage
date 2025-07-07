package com.example.homepage.Member.controller;

import com.example.homepage.Member.dto.GenerationRequestDTO;
import com.example.homepage.Member.service.GenerationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
        try {
            // 서비스에서 existsByGenerationId 체크 후 BAD_REQUEST나 OK를 반환
            return generationService.deleteGeneration(id);
        } catch (EntityNotFoundException ex) {
            // 기수가 없을 때 404
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", ex.getMessage()));
        } catch (Exception ex) {
            // 예기치 못한 에러
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "서버 내부 오류가 발생했습니다."));
        }
    }
}
