package com.example.homepage.RecruitDir.recruit.controller;

import com.example.homepage.RecruitDir.recruit.dto.RecruitRequestDTO;
import com.example.homepage.RecruitDir.recruit.service.RecruitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/api/recruit")
@RequiredArgsConstructor
public class  RecruitController {

    private final RecruitService recruitService;

    @GetMapping
    public ResponseEntity<?> getRecruit() {
        try {
            return ResponseEntity.ok(recruitService.getRecruit());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("서버 오류: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createRecruit(@RequestBody RecruitRequestDTO dto) {
        try {
            recruitService.createRecruit(dto);
            return ResponseEntity.ok().body("모집 일정이 등록되었습니다.");
        } catch (IllegalStateException e) {
            // 데이터 중복 생성 시 에러
            return ResponseEntity.status(400).body("생성 오류: " + e.getMessage());
        } catch (Exception e) {
            // 그 외 예상치 못한 에러
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("서버 오류: " + e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateRecruit(@PathVariable("id") Integer id, @RequestBody RecruitRequestDTO dto) {
        try {
            recruitService.updateRecruit(id, dto);
            return ResponseEntity.ok().body("모집 일정이 수정되었습니다.");
        } catch (IllegalArgumentException e) {
            // 예: 잘못된 ID일 경우
            return ResponseEntity.status(400).body("수정 실패: " + e.getMessage());
        } catch (RuntimeException e) {
            // 예: DB 에러 등 기타 오류
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("서버 오류: " + e.getMessage());
        }
    }
}
