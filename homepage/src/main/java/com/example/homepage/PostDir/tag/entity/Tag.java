package com.example.homepage.PostDir.tag.entity;

import com.example.homepage.PostDir.posttag.entity.PostTag;
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
public class Tag {
    // 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 태그 이름
    private String name;

    @OneToMany(mappedBy = "tag", fetch = FetchType.LAZY)
    private List<PostTag> postTags = new ArrayList<>();

    @Builder
    public Tag(String name) {
        this.name = name;
    }


    // 연관관계 메서드
    public void addPostTag(PostTag postTag) {
        postTags.add(postTag);
        postTag.setTag(this);
    }

    public void removePostTag(PostTag postTag) {
        postTags.remove(postTag);
        postTag.setTag(null);
    }
}
