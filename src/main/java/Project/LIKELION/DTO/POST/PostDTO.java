package Project.LIKELION.DTO.POST;

import lombok.*;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class PostDTO {
    private Integer id;               // 아이디
    private Integer isBest;               // 우수작
    private String title;      // 제목
    private String content;   // 세부 내용
    private String url;       // url
    private String thumbnail;       //썸네일
    private Integer isDeleted;        //삭제여부
    private List<TagDTO> tags;       //태그 dto 매핑

}