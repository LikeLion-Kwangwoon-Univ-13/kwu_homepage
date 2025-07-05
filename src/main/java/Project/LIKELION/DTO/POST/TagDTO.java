package Project.LIKELION.DTO.POST;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class TagDTO {
    private Integer id; //아이디
    private String name; // 태그 이름
}
