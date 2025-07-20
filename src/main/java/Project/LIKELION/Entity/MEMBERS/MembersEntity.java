//package Project.LIKELION.Entity.MEMBERS;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
////연결위함
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Table(name = "members")
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//
//public class MembersEntity {
//    @Id //이건 반드시 한 번만
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Integer id; //pk 아이디
//
//    @Column(name = "generation_id", nullable = false)
//    private Integer generation_id; //pk 기수 아이디
//
//    @Column(columnDefinition = "TEXT", nullable = false)
//    private String profile; //프로필
//
//    @Column(nullable = false, length = 100)
//    private String name; //이름
//    private String position; //직위
//    private String part; //파트
//    private String github; //깃허브
//    private String instagram; //인스타그램
//
//    @Column(nullable = false)
//    private LocalDate created_at; //생성일자
//
//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
//    private List<Members_stacksEntity> members_stacks = new ArrayList<>();
//
//}
