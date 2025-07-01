package Project.LIKELION.Entity.POST;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Tag")
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TagEntity {
    @Id
    @Column(name = "project_id")
    private Integer project_id;

    @Column(columnDefinition = "VARCHAR", nullable = false)
    private String name; //구글폼

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity post;
}
