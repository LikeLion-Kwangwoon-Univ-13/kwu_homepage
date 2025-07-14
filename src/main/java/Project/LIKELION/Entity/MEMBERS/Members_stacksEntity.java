package Project.LIKELION.Entity.MEMBERS;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "members_stacks")
@Getter
@NoArgsConstructor
public class Members_stacksEntity {
    //여기 테이블은 FK 뿐이지만, 엔티티에서 PK가 형식적으로 무조건 필요해서 id로 추가해줌
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MembersEntity member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stack_id")
    private StacksEntity stack;
}
