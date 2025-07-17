package Project.LIKELION.Entity.MEMBERS;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "members_stacks")
@IdClass(Members_stacksEntity.MembersStacksId.class)
@Getter
@NoArgsConstructor
public class Members_stacksEntity {
    //여기 테이블은 FK 뿐이지만, 엔티티에서 PK가 형식적으로 무조건 필요해서 id로 추가해줌

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MembersEntity member;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stack_id")
    private StacksEntity stack;

    public static class MembersStacksId implements Serializable {
        private Integer member;  // 필드명은 위 @Id 필드명과 동일해야 함
        private Integer stack;

        public MembersStacksId() {}
        public MembersStacksId(Integer member, Integer stack) {
            this.member = member;
            this.stack  = stack;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MembersStacksId)) return false;
            MembersStacksId that = (MembersStacksId) o;
            return Objects.equals(member, that.member) &&
                    Objects.equals(stack,  that.stack);
        }
        @Override
        public int hashCode() {
            return Objects.hash(member, stack);
        }
    }

}
