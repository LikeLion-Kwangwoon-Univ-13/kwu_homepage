package com.example.homepage.members.service;

import com.example.homepage.generation.entity.Generation;
import com.example.homepage.generation.repository.GenerationRepository;
import com.example.homepage.members.converter.MembersConverter;
import com.example.homepage.members.dto.MemberCreateRequestDTO;
import com.example.homepage.members.dto.MemberUpdateRequestDTO;
import com.example.homepage.members.dto.MemberWithStacksResponseDTO;
import com.example.homepage.members.entity.Members;
import com.example.homepage.members.repository.MembersRepository;
import com.example.homepage.membersstacks.entity.MembersStacks;
import com.example.homepage.membersstacks.repository.MembersStacksRepository;
import com.example.homepage.stacks.entity.Stacks;
import com.example.homepage.stacks.repository.StacksRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MembersServiceImpl implements MembersService {
    private final MembersRepository membersRepository;
    private final StacksRepository stacksRepository;
    private final MembersStacksRepository membersStacksRepository;
    private final GenerationRepository generationRepository;

    @Override
    public ResponseEntity<?> getMembersByGeneration(int generation) {
        List<Members> members = membersRepository.findByGeneration_Generation(generation);
        if (members.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 정보를 찾을 수 없습니다.");
        }
        List<MemberWithStacksResponseDTO> dtos = members.stream()
                .map(MembersConverter::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(Map.of("members", dtos));
    }

    @Override
    public ResponseEntity<?> addMember(MemberCreateRequestDTO dto) {
        String profile = (dto.getProfile() == null || dto.getProfile().isBlank()) ? "default" : dto.getProfile();

        Generation generation = generationRepository.findByGeneration(dto.getGeneration())
                .orElseThrow(() -> new EntityNotFoundException("기수를 찾을 수 없습니다."));

        Members member = MembersConverter.toEntity(dto, generation);
        membersRepository.save(member);

        for (String stackName : dto.getStacks()) {
            Stacks stack = stacksRepository.findByName(stackName)
                    .orElseGet(() -> stacksRepository.save(Stacks.builder().name(stackName).build()));
            membersStacksRepository.save(MembersStacks.builder()
                    .member(member)
                    .stack(stack)
                    .build());
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("msg", "아기사자가 추가되었습니다."));
    }

    @Override
    public ResponseEntity<?> updateMember(long id, MemberUpdateRequestDTO dto) {
        Members member = membersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 정보를 찾을 수 없습니다."));
        Generation generation = dto.getGeneration() != 0
                ? generationRepository.findByGeneration(dto.getGeneration())
                .orElseThrow(() -> new EntityNotFoundException("기수를 찾을 수 없습니다."))
                : member.getGeneration();

        Members updated = Members.builder()
                .id(member.getId())
                .name(dto.getName() != null ? dto.getName() : member.getName())
                .generation(generation)
                .profile(dto.getProfile() != null ? dto.getProfile() : member.getProfile())
                .position(dto.getPosition() != null ? dto.getPosition() : member.getPosition())
                .part(dto.getPart() != null ? dto.getPart() : member.getPart())
                .github(dto.getGithub() != null ? dto.getGithub() : member.getGithub())
                .instagram(dto.getInstagram() != null ? dto.getInstagram() : member.getInstagram())
                .created_at(member.getCreated_at())
                .projectMembers(member.getProjectMembers())
                .membersStacks(member.getMembersStacks())
                .build();

        membersRepository.save(updated);
        return ResponseEntity.ok(Map.of("msg", "프로젝트 수정이 완료되었습니다."));
    }

    @Override
    public ResponseEntity<?> deleteMember(long id) {
        Members member = membersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 정보를 찾을 수 없습니다."));
        membersRepository.delete(member);
        return ResponseEntity.ok(Map.of("msg", "아기사자가 삭제되었습니다."));
    }
}
