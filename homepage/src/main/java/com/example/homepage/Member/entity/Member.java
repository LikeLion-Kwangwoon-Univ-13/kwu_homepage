package com.example.homepage.Member.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String profile;
    private String name;
    private String position;
    private String part;
    private String github;
    private String instagram;
    private LocalDate createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "generation_id")
    private Generation generation;

    public void updateProfile(String profile) {
        this.profile = profile;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updatePosition(String position) {
        this.position = position;
    }

    public void updatePart(String part) {
        this.part = part;
    }

    public void updateGithub(String github) {
        this.github = github;
    }

    public void updateInstagram(String instagram) {
        this.instagram = instagram;
    }

    public void updateGeneration(Generation generation) {
        this.generation = generation;
    }
}
