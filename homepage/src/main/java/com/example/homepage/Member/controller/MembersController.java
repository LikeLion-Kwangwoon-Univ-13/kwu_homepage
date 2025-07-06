package com.example.homepage.Member.controller;

import com.example.homepage.Member.dto.MemberCreateRequestDTO;
import com.example.homepage.Member.dto.MemberUpdateRequestDTO;
import com.example.homepage.Member.service.MembersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class MembersController {
    private final MembersService membersService;

    @GetMapping(value = "/members/{generation}")
    public ResponseEntity<?> getMembersByGeneration(
            @PathVariable("generation") int generation
    ){
        return membersService.getMembersByGeneration(generation);
    }

    @PostMapping(value = "/manage/newmember")
    public ResponseEntity<?> addNewMember(
            @RequestBody MemberCreateRequestDTO dto
    ){
      return membersService.addMember(dto);
    }

    @PatchMapping(value = "/manage/members/newpost/{id}")
    public ResponseEntity<?> updateMember(
            @PathVariable("id") long id,
            @RequestBody MemberUpdateRequestDTO dto
    ){
        return membersService.updateMember(id, dto);
    }

    @DeleteMapping(value = "/manage/members/{id}/delete")
    public ResponseEntity<?> deleteMember(
            @PathVariable("id") long id
    ){
        return membersService.deleteMember(id);
    }

}
