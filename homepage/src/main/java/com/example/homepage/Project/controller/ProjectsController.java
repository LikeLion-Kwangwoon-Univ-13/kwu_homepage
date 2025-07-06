package com.example.homepage.Project.controller;

import com.example.homepage.Project.dto.ProjectCreateRequestDTO;
import com.example.homepage.Project.dto.ProjectUpdateRequestDTO;
import com.example.homepage.Project.service.ProjectsService;
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
            @RequestParam("section") String section
    ){
        return projectsService.getProjectsBySection(section);
    }

    @GetMapping(value = "/projects/history")
    public ResponseEntity<?> getProjectsHistory(
            @RequestParam("cursor") int cursor, @RequestParam("limit") int limit
    ){
        return projectsService.getProjectsHistory(cursor, limit);
    }

    @GetMapping(value = "/projects/{id}")
    public ResponseEntity<?> getProjectsDetail(
            @PathVariable("id") long id
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
            @PathVariable("id") long id,
            @RequestBody ProjectUpdateRequestDTO dto
    ){
        return projectsService.updateProject(id, dto);
    }

    @DeleteMapping(value = "/manage/project/{id}/delete")
    public ResponseEntity<?> deleteProject(
            @PathVariable("id") long id
    ){
        return projectsService.deleteProject(id);
    }
}
