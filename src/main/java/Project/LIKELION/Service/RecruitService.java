package Project.LIKELION.Service;

import Project.LIKELION.DTO.RecruitDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import Project.LIKELION.Repository.RecruitRepostory;

@Service
@AllArgsConstructor
public class RecruitService {

    private final RecruitRepostory recruitRepostory;

    public RecruitDTO getRecruit() {
        return recruitRepostory.findById(1)
                .map(entity -> RecruitDTO.builder()
                        .id((int) entity.getId())
                        .url(entity.getUrl())
                        .applyDate(entity.getApplyDate())
                        .announceDate(entity.getAnnounceDate())
                        .interviewDate(entity.getInterviewDate())
                        .passDate(entity.getPassDate())
                        .otDate(entity.getOtDate())
                        .build())
                .orElseThrow(() -> new RuntimeException("Recruit not found with id " + 1));
    }

}