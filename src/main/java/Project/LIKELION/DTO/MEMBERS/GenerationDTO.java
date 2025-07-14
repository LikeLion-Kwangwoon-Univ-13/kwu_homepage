package Project.LIKELION.DTO.MEMBERS;

import java.util.List;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class GenerationDTO {
    private Integer id; //아이디
    private Integer generation; //기수
    private List<MembersDTO> members; //일대다 관계
}
