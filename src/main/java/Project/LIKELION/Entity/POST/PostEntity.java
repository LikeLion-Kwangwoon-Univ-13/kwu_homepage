package Project.LIKELION.Entity.POST;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder

public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "isbest")
    private int isBest; //우수작인지 O/X true/false로

    @Column(columnDefinition = "TEXT", nullable = false)
    private String title;
    private String content;
    private String url;
    private String thumbnail;

    @Column(name = "isDeleted")
    private int isDeleted;

    //OneToMany로 수정하기 단방향 주의
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "post_tag",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<TagEntity> tags = new ArrayList<>();

    public List<TagEntity> getTags() {
        return tags;
    }
}
