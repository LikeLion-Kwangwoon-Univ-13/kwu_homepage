package Project.LIKELION.Entity.POST;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Post")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class PostEntity {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "isbest")
    private int isBest; //우수작인지 O/X , true/false

    @Column(columnDefinition = "TEXT", nullable = false)
    private String title;
    private String content;
    private String url;
    private String thumbnail;

    @Column(name = "isDeleted")
    private int isDeleted;

    @OneToMany(
            mappedBy = "post", //포스트 필드가 FK를 가지고 있다
            cascade = CascadeType.ALL, //포스트 엔티티 동작시 태그 엔티티도 동작하도록
            orphanRemoval = true //리스트에서 쓰이지 않는 애들은 알아서 제거
            //이거 잘못 사용하면 좆됨. 물리적 삭제.
    )
    private List<TagEntity> tags = new ArrayList<>() ;

    //일대다 매핑관계로 PostEntity가 여러개의 TagEntity를 가질 수 있도록
    //리스트로 여러 태그를 담을 수 있도록.


}
