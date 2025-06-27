package com.example.homepage.recruit.controller;

import com.example.homepage.recruit.dto.RecruitRequestDTO;
import com.example.homepage.recruit.dto.RecruitResponseDTO;
import com.example.homepage.recruit.service.RecruitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recruit")
@RequiredArgsConstructor
public class  RecruitController {

    private final RecruitService recruitService;

    @GetMapping
    public ResponseEntity<RecruitResponseDTO> getRecruit() {
        return ResponseEntity.ok(recruitService.getRecruit());
    }

    @PostMapping
    public ResponseEntity<?> createRecruit(@RequestBody RecruitRequestDTO dto) {
        recruitService.createRecruit(dto);
        return ResponseEntity.ok().body("모집 일정이 등록되었습니다.");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateRecruit(@PathVariable Integer id, @RequestBody RecruitRequestDTO dto) {
        recruitService.updateRecruit(id, dto);
        return ResponseEntity.ok().body("모집 일정이 수정되었습니다.");
    }
}
