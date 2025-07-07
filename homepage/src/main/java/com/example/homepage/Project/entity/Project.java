package com.example.homepage.Project.entity;

import com.example.homepage.Project.dto.ProjectUpdateRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String lineIntroduction;
    private String introduction;
    private LocalDate startDate;
    private LocalDate endDate;
    private int isStar;
    private int generation;
    private String part;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    public void updateFromDto(ProjectUpdateRequestDTO dto) {
        if (dto.getTitle() != null) this.title = dto.getTitle();
        if (dto.getLine_introduction() != null) this.lineIntroduction = dto.getLine_introduction();
        if (dto.getIntroduction() != null) this.introduction = dto.getIntroduction();
        if (dto.getPart() != null) this.part = dto.getPart();
        if (dto.getStart_Date() != null) this.startDate = dto.getStart_Date();
        if (dto.getEnd_Date() != null) this.endDate = dto.getEnd_Date();
    }

}
