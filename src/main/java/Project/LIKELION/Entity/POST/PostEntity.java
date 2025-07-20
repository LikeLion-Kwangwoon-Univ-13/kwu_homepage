package Project.LIKELION.Entity.POST;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "post")
@NoArgsConstructor
@AllArgsConstructor
@Getter

public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "isbest")
    private boolean isBest; //우수작인지 O/X true/false로

    @Column(columnDefinition = "TEXT", nullable = false)
    private String title; //제목
    private String content; //세부 내용
    private String url; //url
    private String thumbnail; //썸네일

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createdAt;

    @Column(name = "isDeleted")
    private boolean isDeleted; //삭제 여부

    //setter 대체 메서드.
    public void updateIsBest(boolean isBest) {
        this.isBest = isBest;
    }
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
    public void updateDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    @Builder
    public PostEntity(Boolean isBest, Boolean isDeleted, Integer id,  String title, String content, String url, String thumbnail) {
        this.isBest = isBest;
//        this.id = id;
        this.title = title;
        this.content = content;
        this.url = url;
        this.thumbnail = thumbnail;
        this.isDeleted = isDeleted;
    }

}
