package Project.LIKELION.DTO.MEMBERS;

import lombok.*;
import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class MembersDTO {
    private Integer id; //아이디
    private Integer generation_id; //기수 아이디
    private String profile; //프로필 사진
    private String name; //이름
    private String position; //직위
    private String part; //파트
    private String github; //깃허브
    private String instagram; //인스타그램
    private LocalDate created_at; //생성일자
}
