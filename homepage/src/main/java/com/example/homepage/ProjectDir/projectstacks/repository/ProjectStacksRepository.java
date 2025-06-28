package com.example.homepage.ProjectDir.projectstacks.repository;

import com.example.homepage.ProjectDir.projectstacks.entity.ProjectStacks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectStacksRepository extends JpaRepository<ProjectStacks, Long> {
}
