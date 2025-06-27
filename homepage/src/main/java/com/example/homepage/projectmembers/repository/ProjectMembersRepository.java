package com.example.homepage.projectmembers.repository;

import com.example.homepage.projectmembers.entity.ProjectMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectMembersRepository extends JpaRepository<ProjectMembers, Long> {
}
