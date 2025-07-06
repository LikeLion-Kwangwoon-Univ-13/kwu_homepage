package com.example.homepage.Post.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    // 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 우수작인지
    private Boolean isBest;
    // 제목
    private String title;
    // 세부 내용
    private String content;
    // URL
    private String url;
    // 썸네일
    private String thumbnail;
    // 작성 시간
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
    // 삭제 여부
    private Boolean isDeleted;

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void updateUrl(String url) {
        this.url = url;
    }

    public void updateThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void updateIsBest(Boolean isBest) { this.isBest = isBest; }

    @Builder
    public Post(String title, String content, String url, String thumbnail) {
        this.title = title;
        this.content = content;
        this.url = url;
        this.thumbnail = thumbnail;

        // 초기화
        this.isBest = false;
        this.isDeleted = false;
    }
}