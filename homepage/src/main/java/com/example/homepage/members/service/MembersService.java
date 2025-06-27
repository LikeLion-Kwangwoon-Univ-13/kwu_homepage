package com.example.homepage.members.service;

import com.example.homepage.members.dto.MemberCreateRequestDTO;
import com.example.homepage.members.dto.MemberUpdateRequestDTO;
import org.springframework.http.ResponseEntity;

public interface MembersService {
    ResponseEntity<?> getMembersByGeneration(int generation);
    ResponseEntity<?> addMember(MemberCreateRequestDTO dto);
    ResponseEntity<?> updateMember(long id, MemberUpdateRequestDTO dto);
    ResponseEntity<?> deleteMember(long id);
}
