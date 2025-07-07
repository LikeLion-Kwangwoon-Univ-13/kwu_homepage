package com.example.homepage.Member.repository;

import com.example.homepage.Member.entity.ProjectMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectMembersRepository extends JpaRepository<ProjectMembers, Long> {
    void deleteAllByMemberId(Long memberId);
    List<ProjectMembers> findAllByProjectId(Long projectId);
    void deleteAllByProjectId(Long projectId);
}
