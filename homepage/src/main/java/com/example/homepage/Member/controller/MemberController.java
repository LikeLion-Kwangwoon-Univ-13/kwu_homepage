package com.example.homepage.Member.controller;

import com.example.homepage.Member.dto.MemberCreateRequestDTO;
import com.example.homepage.Member.dto.MemberUpdateRequestDTO;
import com.example.homepage.Member.service.MemberService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping(value = "/members/{generation}")
    public ResponseEntity<?> getMembersByGeneration(
            @PathVariable("generation") int generation
    ){
        try {
            return memberService.getMembersByGeneration(generation);
        } catch (EntityNotFoundException ex) {
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

    @PostMapping(value = "/manage/newmember")
    public ResponseEntity<?> addNewMember(
            @RequestBody MemberCreateRequestDTO dto
    ){
        try {
            return memberService.addMember(dto);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "서버 내부 오류가 발생했습니다."));
        }
    }

    @PatchMapping(value = "/manage/members/{id}")
    public ResponseEntity<?> updateMember(
            @PathVariable("id") long id,
            @RequestBody MemberUpdateRequestDTO dto
    ){
        return memberService.updateMember(id, dto);
    }

    @DeleteMapping(value = "/manage/members/{id}")
    public ResponseEntity<?> deleteMember(
            @PathVariable("id") long id
    ){
        return memberService.deleteMember(id);
    }

}
