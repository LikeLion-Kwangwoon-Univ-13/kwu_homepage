package Project.LIKELION.Entity.MEMBERS;

import jakarta.persistence.*;
import lombok.*;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "stacks")
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class StacksEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(columnDefinition = "VARCHAR", nullable = false)
    private String name;

    @OneToMany(mappedBy = "stack", cascade = CascadeType.ALL )
    private List<Members_stacksEntity> members_stacks = new ArrayList<>();
}
