package com.example.homepage.Project.controller;

import com.example.homepage.Project.dto.ProjectCreateRequestDTO;
import com.example.homepage.Project.dto.ProjectUpdateRequestDTO;
import com.example.homepage.Project.service.ProjectService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping(value = "/projects")
    public ResponseEntity<?> getProjectsHome(
            @RequestParam("section") String section
    ){
        return projectService.getProjectsBySection(section);
    }

    @GetMapping(value = "/projects/history")
    public ResponseEntity<?> getProjectsHistory(
            @RequestParam("cursor") int cursor, @RequestParam("limit") int limit
    ){
        return projectService.getProjectsHistory(cursor, limit);
    }

    @GetMapping(value = "/projects/{id}")
    public ResponseEntity<?> getProjectsDetail(
            @PathVariable("id") long id
    ){
        return projectService.getProjectDetail(id);
    }

    @PostMapping(value = "/manage/newproject")
    public ResponseEntity<?> createProject(
            @RequestBody ProjectCreateRequestDTO dto
    ){
        try{
            return projectService.createProject(dto);
        }
        catch (EntityNotFoundException ex){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", ex.getMessage()));
        }
        catch(Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "서버 내부 오류가 발생했습니다."));
        }
    }

    @PatchMapping(value = "/manage/project/newpost/{id}")
    public ResponseEntity<?> updateProject(
            @PathVariable("id") long id,
            @RequestBody ProjectUpdateRequestDTO dto
    ){
        return projectService.updateProject(id, dto);
    }

    @DeleteMapping(value = "/manage/project/{id}/delete")
    public ResponseEntity<?> deleteProject(
            @PathVariable("id") long id
    ){
        return projectService.deleteProject(id);
    }
}
