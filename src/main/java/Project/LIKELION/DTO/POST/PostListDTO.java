//페이지네이션을 위해 List 클래스를 만듦.

package Project.LIKELION.DTO.POST;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PostListDTO {
    //베스트글과 일반글을 나누기 위함.
    private List<PostResponseDTO> posts;
    private List<PostResponseDTO> best;

    @Builder
    public PostListDTO(List<PostResponseDTO> posts) {
        this.posts = posts;
    }
}
