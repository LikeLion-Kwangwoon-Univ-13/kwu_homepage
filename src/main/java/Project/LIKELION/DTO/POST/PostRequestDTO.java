package Project.LIKELION.DTO.POST;

import Project.LIKELION.Entity.POST.PostEntity;
import lombok.*;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PostRequestDTO {     // 우수작
    private String title;      // 제목
    private String content;   // 세부 내용
    private boolean isBest;
    private String url;       // url
    private String thumbnail;       //썸네일
    private List<String> tags;       //태그 dto 매핑

    public static PostRequestDTO fromEntity(PostEntity post) {
        return PostRequestDTO.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .url(post.getUrl())
                .thumbnail(post.getThumbnail())
                .build();
    }
}