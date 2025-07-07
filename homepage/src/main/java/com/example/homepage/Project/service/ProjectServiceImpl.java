package com.example.homepage.Project.service;

import com.example.homepage.Member.entity.Member;
import com.example.homepage.Member.repository.MemberRepository;
import com.example.homepage.Project.dto.*;
import com.example.homepage.Project.entity.ProjectImage;
import com.example.homepage.Project.repository.ProjectImageRepository;
import com.example.homepage.Member.entity.ProjectMembers;
import com.example.homepage.Member.repository.ProjectMembersRepository;
import com.example.homepage.Project.converter.ProjectsConverter;
import com.example.homepage.Project.repository.ProjectRepository;
import com.example.homepage.Project.entity.Project;
import com.example.homepage.Project.entity.ProjectStacks;
import com.example.homepage.Project.repository.ProjectStacksRepository;
import com.example.homepage.Member.entity.Stack;
import com.example.homepage.Member.repository.StacksRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMembersRepository projectMembersRepository;
    private final ProjectStacksRepository projectStacksRepository;
    private final StacksRepository stacksRepository;
    private final MemberRepository memberRepository;
    private final ProjectImageRepository projectImageRepository;

    @Override
    public String getProjectImage(long id){
        return projectImageRepository
                .findAllByProjectIdOrderByOrderIndexAsc(id)
                .stream()
                .map(ProjectImage::getUrl)
                .findFirst()
                .orElse("default");
    }

    @Override
    public ResponseEntity<?> getProjectsBySection(String section) {
        if (!section.equals("home")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid section");
        }

        List<Project> top5 = projectRepository.findTop5ByOrderByCreatedAtDesc();
        List<ProjectHomeResponseDTO> dtos = top5.stream()
                .map(p -> {
                    // 프로젝트별 이미지 리스트에서 첫 번째 URL 꺼내기
                    String imageUrl = getProjectImage(p.getId());

                    // Project + imageUrl 넘겨서 DTO 생성
                    return ProjectsConverter.toHomeDTO(p, imageUrl);
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(Map.of("projects", dtos));
    }

    @Override
    public ResponseEntity<?> getProjectsHistory(int cursor, int limit) {
        // 페이징
        Pageable pageable = PageRequest.of(cursor, limit, Sort.by("createdAt").descending());

        Page<Project> projectPage = projectRepository.findAllByOrderByCreatedAtDesc(pageable);
        List<Project> projects = projectPage.getContent();

        if (projects.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 정보를 찾을 수 없습니다.");
        }

        List<ProjectHistoryResponseDTO> dtos = projects.stream()
                .map(p -> {
                    // 대표 이미지 URL 가져오기
                    String imageUrl = getProjectImage(p.getId());

                    // Converter에 project와 imageUrl 함께 전달
                    return ProjectsConverter.toHistoryDTO(p, imageUrl);
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(Map.of("projects", dtos));
    }

    @Override
    public ResponseEntity<?> getProjectDetail(long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 정보를 찾을 수 없습니다."));

        List<Member> projectMembers = projectMembersRepository
                .findAllByProjectId(id)
                .stream()
                .map(ProjectMembers::getMember)
                .collect(Collectors.toList());

        List<String> projectStacks = projectStacksRepository
                .findAllByProjectId(id)
                .stream()
                .map(ps -> ps.getStack().getName())
                .collect(Collectors.toList());

        String imageUrl = getProjectImage(id);

        ProjectDetailResponseDTO dto = ProjectsConverter.toDetailDTO(project, projectMembers, projectStacks, imageUrl);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<?> createProject(ProjectCreateRequestDTO dto) {
        Project project = ProjectsConverter.toEntity(dto);
        Project saved = projectRepository.save(project);

        projectImageRepository.save(ProjectImage.builder()
                .project(saved)
                .url(dto.getUrl())
                .orderIndex(0)
                .build());

        dto.getProjectMember().forEach((name) -> {
            Member member = memberRepository.findByName(name)
                    .orElseThrow(() -> new EntityNotFoundException("멤버를 찾을 수 없습니다."));
            projectMembersRepository.save(ProjectMembers.builder()
                    .member(member)
                    .project(saved)
                    .build());
        });

        for (String name : dto.getStacks()) {
            Stack stack = stacksRepository.findByName(name)
                    .orElseGet(() -> stacksRepository.save(Stack.builder().name(name).build()));
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
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 정보를 찾을 수 없습니다."));

        // 멤버, 스택 수정
        project.updateFromDto(dto);
        projectRepository.save(project);
        return ResponseEntity.ok(Map.of("msg", "프로젝트 수정이 완료되었습니다."));
    }

    @Override
    public ResponseEntity<?> deleteProject(long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 정보를 찾을 수 없습니다."));

        // 연관된 이미지 삭제
        projectImageRepository.deleteAllByProjectId(id);
        // 연관된 멤버 삭제
        projectMembersRepository.deleteAllByProjectId(id);
        // 연관된 스택 삭제
        projectStacksRepository.deleteAllByProjectId(id);

        // 프로젝트 삭제
        projectRepository.delete(project);
        return ResponseEntity.ok(Map.of("msg", "프로젝트가 삭제되었습니다."));
    }
}
