package com.example.homepage.Project.entity;

import com.example.homepage.Member.entity.Stacks;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectStacks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Projects project;

    @ManyToOne
    @JoinColumn(name = "stack_id")
    private Stacks stack;
}
