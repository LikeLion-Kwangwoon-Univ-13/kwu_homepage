//package Project.LIKELION.Entity.MEMBERS;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Entity
//@Table(name = "stacks")
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//
//public class StacksEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Integer id;
//
//    @Column(nullable = false, length = 100)
//    private String name;
//
//    @OneToMany(mappedBy = "stack", cascade = CascadeType.ALL )
//    private List<Members_stacksEntity> members_stacks = new ArrayList<>();
//}
