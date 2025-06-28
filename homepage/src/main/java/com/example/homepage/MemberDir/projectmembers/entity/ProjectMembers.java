package com.example.homepage.MemberDir.projectmembers.entity;

import com.example.homepage.MemberDir.members.entity.Members;
import com.example.homepage.ProjectDir.projects.entity.Projects;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProjectMembers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Projects project;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Members member;
}
