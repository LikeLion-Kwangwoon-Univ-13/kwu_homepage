//테이블 매핑 느낌
package Project.LIKELION.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Recruit")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class RecruitEntity {
    @Id
    @Column(name = "id")
    private long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String url;

    @Column(nullable = false)
    private LocalDate applyDate;
    private LocalDate announceDate;
    private LocalDate interviewDate;
    private LocalDate passDate;
    private LocalDate otDate;
}