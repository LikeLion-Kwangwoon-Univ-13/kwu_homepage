package com.example.homepage.projects.controller;

import com.example.homepage.projects.dto.ProjectCreateRequestDTO;
import com.example.homepage.projects.dto.ProjectUpdateRequestDTO;
import com.example.homepage.projects.service.ProjectsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class ProjectsController {
    private final ProjectsService projectsService;

    @GetMapping(value = "/projects")
    public ResponseEntity<?> getProjectsHome(
            @RequestParam String section
    ){
        return projectsService.getProjectsBySection(section);
    }

    @GetMapping(value = "/projects/history")
    public ResponseEntity<?> getProjectsHistory(
            @RequestParam int cursor, @RequestParam int limit
    ){
        return projectsService.getProjectsHistory(cursor, limit);
    }

    @GetMapping(value = "/projects/{id}")
    public ResponseEntity<?> getProjectsDetail(
            @PathVariable long id
    ){
        return projectsService.getProjectDetail(id);
    }

    @PostMapping(value = "/manage/newproject")
    public ResponseEntity<?> createProject(
            @RequestBody ProjectCreateRequestDTO dto
    ){
      return projectsService.createProject(dto);
    }

    @PatchMapping(value = "/manage/project/newpost/{id}")
    public ResponseEntity<?> updateProject(
            @PathVariable long id,
            @RequestBody ProjectUpdateRequestDTO dto
    ){
        return projectsService.updateProject(id, dto);
    }

    @DeleteMapping("/manage/project/{id}/delete")
    public ResponseEntity<?> deleteProject(
            @PathVariable long id
    ){
        return projectsService.deleteProject(id);
    }
}
