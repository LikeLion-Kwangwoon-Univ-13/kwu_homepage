//테이블 매핑 느낌
package Project.LIKELION.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import Project.LIKELION.DTO.RecruitDTO;

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

    @Column(nullable = true)
    private LocalDate documentDate;
    private LocalDate candidateDate;
    private LocalDate interviewDate;
    private LocalDate acceptDate;
    private LocalDate otDate;

    public void update(RecruitDTO dto) {
        this.url = dto.getUrl();
        this.documentDate = dto.getDocumentDate();
        this.candidateDate = dto.getCandidateDate();
        this.interviewDate = dto.getInterviewDate();
        this.acceptDate = dto.getAcceptDate();
        this.otDate = dto.getOtDate();
    }

}