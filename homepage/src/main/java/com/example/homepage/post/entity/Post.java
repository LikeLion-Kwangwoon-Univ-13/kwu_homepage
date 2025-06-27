package com.example.homepage.post.entity;

import com.example.homepage.posttag.entity.PostTag;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private Integer id;
    // 우수작인지
    private Integer isBest = 0;
    // 제목
    private String title;
    // 세부 내용
    private String content;
    // URL
    private String url;
    // 썸네일
    private String thumbnail;
    // 삭제 여부
    private Integer isDeleted = 0;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
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

    @Builder
    public Post(String title, String content, String url, String thumbnail) {
        this.title = title;
        this.content = content;
        this.url = url;
        this.thumbnail = thumbnail;
    }
}
