package com.example.homepage.Member.service;

import com.example.homepage.Member.converter.MembersConverter;
import com.example.homepage.Member.repository.*;
import com.example.homepage.Member.entity.Generation;
import com.example.homepage.Member.dto.MemberCreateRequestDTO;
import com.example.homepage.Member.dto.MemberUpdateRequestDTO;
import com.example.homepage.Member.dto.MemberWithStacksResponseDTO;
import com.example.homepage.Member.entity.Member;
import com.example.homepage.Member.entity.MemberStacks;
import com.example.homepage.Member.entity.Stack;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final StacksRepository stacksRepository;
    private final MemberStacksRepository memberStacksRepository;
    private final GenerationRepository generationRepository;
    private final ProjectMembersRepository projectMembersRepository;

    @Override
    public ResponseEntity<?> getMembersByGeneration(int generation) {
        // 기수 멤버 리스트
        List<Member> members;
        if(generation == 0){
            Generation recentGen = generationRepository
                    .findFirstByOrderByGenerationDesc()
                    .orElseThrow(() -> new EntityNotFoundException("등록된 기수가 없습니다."));

            members = memberRepository.findByGeneration_Generation(recentGen.getGeneration());
        }
        else{
            members = memberRepository.findByGeneration_Generation(generation);
        }

        List<MemberWithStacksResponseDTO> dtos = members.stream()
                .map(m -> {
                    List<String> stacks = memberStacksRepository.findStacksByMemberId(m.getId())
                            .stream()
                            .map(s -> s.getName())
                            .collect(Collectors.toList());
                    return MembersConverter.toDto(m, stacks);
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(Map.of("members", dtos));
    }

    @Override
    public ResponseEntity<?> addMember(MemberCreateRequestDTO dto) {
        Generation generation = generationRepository.findByGeneration(dto.getGeneration())
                .orElseThrow(() -> new EntityNotFoundException("기수를 찾을 수 없습니다."));

        // 기본 이미지 설정
        String profile = Optional.ofNullable(dto.getProfile())
                .filter(s -> !s.isBlank())
                .orElse("default");

        Member member = MembersConverter.toEntity(dto, generation);
        member.updateProfile(profile);
        // 멤버 저장
        memberRepository.save(member);

        // Stack 저장
        for (String stackName : dto.getStacks()) {
            Stack stack = stacksRepository.findByName(stackName)
                    .orElseGet(() -> stacksRepository.save(Stack.builder().name(stackName).build()));
            memberStacksRepository.save(MemberStacks.builder()
                    .member(member)
                    .stack(stack)
                    .build());
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("msg", "사자가 추가되었습니다."));
    }

    @Override
    public ResponseEntity<?> updateMember(long id, MemberUpdateRequestDTO dto) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 정보를 찾을 수 없습니다."));

        Generation generation = dto.getGeneration() != 0
                ? generationRepository.findByGeneration(dto.getGeneration())
                .orElseThrow(() -> new EntityNotFoundException("기수를 찾을 수 없습니다."))
                : member.getGeneration();

        // 프로필, 이름, 직책 등 선택적 업데이트
        if (dto.getProfile() != null && !dto.getProfile().isBlank()) {
            member.updateProfile(dto.getProfile());
        }
        if (dto.getName() != null && !dto.getName().isBlank()) {
            member.updateName(dto.getName());
        }
        if (dto.getPosition() != null && !dto.getPosition().isBlank()) {
            member.updatePosition(dto.getPosition());
        }
        if (dto.getPart() != null && !dto.getPart().isBlank()) {
            member.updatePart(dto.getPart());
        }
        if (dto.getGithub() != null && !dto.getGithub().isBlank()) {
            member.updateGithub(dto.getGithub());
        }
        if (dto.getInstagram() != null && !dto.getInstagram().isBlank()) {
            member.updateInstagram(dto.getInstagram());
        }

        // STACK 수정


        memberRepository.save(member);
        return ResponseEntity.ok(Map.of("msg", "사자 수정이 완료되었습니다."));
    }

    @Override
    public ResponseEntity<?> deleteMember(long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 정보를 찾을 수 없습니다."));

        // 2) 연관 스택 연결 삭제 (FK 제약 방지)
        memberStacksRepository.deleteAllByMemberId(id);
        projectMembersRepository.deleteAllByMemberId(id);

        memberRepository.delete(member);
        return ResponseEntity.ok(Map.of("msg", "아기사자가 삭제되었습니다."));
    }
}
