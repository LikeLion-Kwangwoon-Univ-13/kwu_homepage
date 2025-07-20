package Project.LIKELION.DTO.POST;

import Project.LIKELION.Entity.POST.PostEntity;
import lombok.*;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder

public class PostResponseDTO {
    private Integer id;               // 아이디     // 우수작
    private String title;      // 제목
    private String content;   // 세부 내용
    private String url;       // url
    private String thumbnail;       //썸네일
    private List<String> tags;

    public static Object fromEntity (PostEntity post) {
        return PostResponseDTO.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .url(post.getUrl())
                .thumbnail(post.getThumbnail())
                .build();
    }
}

