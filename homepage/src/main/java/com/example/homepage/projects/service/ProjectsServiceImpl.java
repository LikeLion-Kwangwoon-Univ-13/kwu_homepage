package com.example.homepage.projects.service;

import com.example.homepage.members.entity.Members;
import com.example.homepage.members.repository.MembersRepository;
import com.example.homepage.projectimage.entity.ProjectImage;
import com.example.homepage.projectimage.repository.ProjectImageRepository;
import com.example.homepage.projectmembers.entity.ProjectMembers;
import com.example.homepage.projectmembers.repository.ProjectMembersRepository;
import com.example.homepage.projects.converter.ProjectsConverter;
import com.example.homepage.projects.dto.*;
import com.example.homepage.projects.entity.Projects;
import com.example.homepage.projects.repository.ProjectsRepository;
import com.example.homepage.projectstacks.entity.ProjectStacks;
import com.example.homepage.projectstacks.repository.ProjectStacksRepository;
import com.example.homepage.stacks.entity.Stacks;
import com.example.homepage.stacks.repository.StacksRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectsServiceImpl implements ProjectsService {
    private final ProjectsRepository projectsRepository;
    private final ProjectMembersRepository projectMembersRepository;
    private final ProjectStacksRepository projectStacksRepository;
    private final StacksRepository stacksRepository;
    private final MembersRepository membersRepository;
    private final ProjectImageRepository projectImageRepository;

    @Override
    public ResponseEntity<?> getProjectsBySection(String section) {
        if (!section.equals("home")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid section");
        }
        List<Projects> top5 = projectsRepository.findTop5ByOrderById();
        List<ProjectHomeResponseDTO> dtos = top5.stream()
                .map(ProjectsConverter::toHomeDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(Map.of("projects", dtos));
    }

    @Override
    public ResponseEntity<?> getProjectsHistory(int cursor, int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        List<Projects> projects = projectsRepository.findByIdGreaterThanOrderByIdAsc(cursor, pageable);
        if (projects.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 정보를 찾을 수 없습니다.");
        }
        List<ProjectHistoryResponseDTO> dtos = projects.stream()
                .map(ProjectsConverter::toHistoryDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(Map.of("projects", dtos));
    }

    @Override
    public ResponseEntity<?> getProjectDetail(long id) {
        Projects project = projectsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 정보를 찾을 수 없습니다."));
        ProjectDetailResponseDTO dto = ProjectsConverter.toDetailDTO(project);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<?> createProject(ProjectCreateRequestDTO dto) {
        Projects project = ProjectsConverter.toEntity(dto);
        Projects saved = projectsRepository.save(project);

        projectImageRepository.save(ProjectImage.builder()
                .project(saved)
                .url(dto.getUrl())
                .orderIndex(0)
                .build());

        dto.getProject_member().forEach((name, position) -> {
            Members member = membersRepository.findByName(name)
                    .orElseThrow(() -> new EntityNotFoundException("멤버를 찾을 수 없습니다."));
            projectMembersRepository.save(ProjectMembers.builder()
                    .member(member)
                    .project(saved)
                    .build());
        });

        for (String name : dto.getStacks()) {
            Stacks stack = stacksRepository.findByName(name)
                    .orElseGet(() -> stacksRepository.save(Stacks.builder().name(name).build()));
            projectStacksRepository.save(ProjectStacks.builder()
                    .stack(stack)
                    .project(saved)
                    .build());
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("msg", "프로젝트를 추가하였습니다."));
    }

    @Override
    public ResponseEntity<?> updateProject(long id, ProjectUpdateRequestDTO dto) {
        Projects project = projectsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 정보를 찾을 수 없습니다."));
        project.updateFromDto(dto);
        projectsRepository.save(project);
        return ResponseEntity.ok(Map.of("msg", "프로젝트 수정이 완료되었습니다."));
    }

    @Override
    public ResponseEntity<?> deleteProject(long id) {
        Projects project = projectsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 정보를 찾을 수 없습니다."));
        projectsRepository.delete(project);
        return ResponseEntity.ok(Map.of("msg", "프로젝트가 삭제되었습니다."));
    }
}
