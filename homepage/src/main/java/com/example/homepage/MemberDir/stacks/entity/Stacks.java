package com.example.homepage.MemberDir.stacks.entity;

import com.example.homepage.MemberDir.membersstacks.entity.MembersStacks;
import com.example.homepage.ProjectDir.projectstacks.entity.ProjectStacks;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Stacks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(mappedBy = "stack", cascade = CascadeType.ALL)
    private List<MembersStacks> membersStacks;

    @OneToMany(mappedBy = "stack", cascade = CascadeType.ALL)
    private List<ProjectStacks> projectStacks;
}
