package com.example.homepage.ProjectDir.projects.service;

import com.example.homepage.ProjectDir.projects.dto.ProjectCreateRequestDTO;
import com.example.homepage.ProjectDir.projects.dto.ProjectUpdateRequestDTO;
import org.springframework.http.ResponseEntity;

public interface ProjectsService {
    ResponseEntity<?> getProjectsBySection(String section);
    ResponseEntity<?> getProjectsHistory(int cursor, int limit);
    ResponseEntity<?> getProjectDetail(long id);
    ResponseEntity<?> createProject(ProjectCreateRequestDTO dto);
    ResponseEntity<?> updateProject(long id, ProjectUpdateRequestDTO dto);
    ResponseEntity<?> deleteProject(long id);
}
