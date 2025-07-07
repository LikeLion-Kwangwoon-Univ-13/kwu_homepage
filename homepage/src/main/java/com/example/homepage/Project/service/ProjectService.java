package com.example.homepage.Project.service;

import com.example.homepage.Project.dto.ProjectCreateRequestDTO;
import com.example.homepage.Project.dto.ProjectUpdateRequestDTO;
import org.springframework.http.ResponseEntity;

public interface ProjectService {
    String getProjectImage(long id);
    ResponseEntity<?> getProjectsBySection(String section);
    ResponseEntity<?> getProjectsHistory(int cursor, int limit);
    ResponseEntity<?> getProjectDetail(long id);
    ResponseEntity<?> createProject(ProjectCreateRequestDTO dto);
    ResponseEntity<?> updateProject(long id, ProjectUpdateRequestDTO dto);
    ResponseEntity<?> deleteProject(long id);
}
