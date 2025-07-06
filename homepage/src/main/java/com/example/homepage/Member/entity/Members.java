package com.example.homepage.Member.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Members {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String profile;
    private String name;
    private String position;
    private String part;
    private String github;
    private String instagram;
    private LocalDate created_at;

    @ManyToOne
    @JoinColumn(name = "generation_id")
    private Generation generation;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<ProjectMembers> projectMembers;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MembersStacks> membersStacks;
}
