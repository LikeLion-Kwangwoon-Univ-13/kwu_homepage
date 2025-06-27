package com.example.homepage.members.controller;

import com.example.homepage.members.dto.MemberCreateRequestDTO;
import com.example.homepage.members.dto.MemberUpdateRequestDTO;
import com.example.homepage.members.service.MembersService;
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
            @PathVariable int generation
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
            @PathVariable long id,
            @RequestBody MemberUpdateRequestDTO dto
    ){
        return membersService.updateMember(id, dto);
    }

    @DeleteMapping(value = "/manage/members/{id}/delete")
    public ResponseEntity<?> deleteMember(
            @PathVariable long id
    ){
        return membersService.deleteMember(id);
    }

}
