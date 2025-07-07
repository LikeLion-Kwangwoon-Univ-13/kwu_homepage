package com.example.homepage.Member.service;

import com.example.homepage.Member.dto.MemberCreateRequestDTO;
import com.example.homepage.Member.dto.MemberUpdateRequestDTO;
import org.springframework.http.ResponseEntity;

public interface MemberService {
    ResponseEntity<?> getMembersByGeneration(int generation);
    ResponseEntity<?> addMember(MemberCreateRequestDTO dto);
    ResponseEntity<?> updateMember(long id, MemberUpdateRequestDTO dto);
    ResponseEntity<?> deleteMember(long id);
}
