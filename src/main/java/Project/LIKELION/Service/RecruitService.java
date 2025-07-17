package Project.LIKELION.Service;

import Project.LIKELION.DTO.RecruitDTO;
import Project.LIKELION.Entity.RecruitEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import Project.LIKELION.Repository.RecruitRepostory;
import org.springframework.web.server.ResponseStatusException;

@Service
//@AllArgsConstructor //이거 말고 아래 어노테이션을 써야 하는 이유는 무엇
@RequiredArgsConstructor
public class RecruitService {
    private final RecruitRepostory recruitRepository;

    //GET/api/recruit
    @Transactional(readOnly = true)
    public RecruitDTO getRecruit() {
        RecruitEntity recruitEntity = recruitRepository.findAll()
                //GET은 404 에러만
                .stream()
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recruit 데이터가 없습니다."));

        return mapToDTO(recruitEntity);
    }


    public RecruitDTO mapToDTO(RecruitEntity recruit) {
        return RecruitDTO.builder()
                        .id((int) recruit.getId())
                        .url(recruit.getUrl())
                        .documentDate(recruit.getDocumentDate())
                        .candidateDate(recruit.getCandidateDate())
                        .interviewDate(recruit.getInterviewDate())
                        .acceptDate(recruit.getAcceptDate())
                        .otDate(recruit.getOtDate())
                        .build();
    }

    //POST
    @Transactional
    public RecruitDTO createRecruit(RecruitDTO recruitDTO) {
        //이미 데이터가 있다면 400 에러
        if(recruitRepository.count() >0 ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Recruit 데이터는 하나만 생성할 수 있습니다.");
        }

        //DTO-> entity 변환
        RecruitEntity recruitentity = RecruitEntity.builder()
                .url(recruitDTO.getUrl())
                .documentDate(recruitDTO.getDocumentDate())
                .candidateDate(recruitDTO.getCandidateDate())
                .interviewDate(recruitDTO.getInterviewDate())
                .acceptDate(recruitDTO.getAcceptDate())
                .otDate(recruitDTO.getOtDate())
                .build();

        recruitRepository.save(recruitentity);
        return mapToDTO(recruitentity);
    }

    //patch
    @Transactional
    public void updateRecruit(Integer id, RecruitDTO recruitDTO) {

        //데이터가 존재하지 않을 경우
        RecruitEntity existing = recruitRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "해당 ID의 모집 일정이 존재하지 않습니다."));

        RecruitEntity updated = RecruitEntity.builder()
                .id(id)
                .url(recruitDTO.getUrl())
                .documentDate(recruitDTO.getDocumentDate())
                .candidateDate(recruitDTO.getCandidateDate())
                .interviewDate(recruitDTO.getInterviewDate())
                .acceptDate(recruitDTO.getAcceptDate())
                .otDate(recruitDTO.getOtDate())
                .build();

        recruitRepository.save(updated);
    }
}