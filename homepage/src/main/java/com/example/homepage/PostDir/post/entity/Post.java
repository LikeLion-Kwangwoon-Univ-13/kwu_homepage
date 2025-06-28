package com.example.homepage.PostDir.post.entity;

import com.example.homepage.PostDir.posttag.entity.PostTag;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostTag> postTags = new ArrayList<>();

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

    // 연관관계 메서드
    public void addPostTag(PostTag postTag) {
        postTags.add(postTag);
        postTag.setPost(this);
    }

    public void removePostTag(PostTag postTag) {
        postTags.remove(postTag);
        postTag.setPost(null);
    }
}
