package Project.LIKELION.Entity.POST;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "postTags")
@Getter
@NoArgsConstructor

public class PostTagEntity {
    //여기 테이블은 FK뿐임. ManyToOne 연결을 위해 씀
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private PostEntity post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private TagEntity tag;

    @Builder
    public PostTagEntity(PostEntity post, TagEntity tag) {
        this.post = post;
        this.tag = tag;
    }

}
