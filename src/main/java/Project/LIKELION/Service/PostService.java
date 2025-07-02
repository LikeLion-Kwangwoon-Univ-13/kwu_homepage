package Project.LIKELION.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import Project.LIKELION.Repository.PostRepository; //DB 접근용
import Project.LIKELION.DTO.POST.PostDTO; //반환할 DTO 객체

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public PostDTO getPostById(Integer id) { //ID로 조회 API 필요하니까 ById
        return postRepository.findById(id)
                .map(entity -> PostDTO.builder()
                        .id(entity.getId())
                        .isBest(entity.getIsBest()) //엥시발??
                        .title(entity.getTitle())
                        .content(entity.getContent())
                        .url(entity.getUrl())
                        .thumbnail(entity.getThumbnail())
                        .isDeleted(entity.getIsDeleted())
                        //서비스에서 리스트 매핑 방법
//                        .List(entity.getTags)<>()
                        //아 서비스에서 리스트 매핑하는 방법을 모르겠음;;
                        //도저히 찾아봐도 안 나옴
                        .build())

                .orElseThrow(()-> new RuntimeException("Post Not Found"));


    }


    public PostDTO createPost(PostDTO dto) {
        return postRepository.findById(dto.getId())
                .map(entity -> PostDTO.builder()
                        .id(entity.getId())
                        .isBest(entity.getIsBest()) //엥시발??
                        .title(entity.getTitle())
                        .content(entity.getContent())
                        .url(entity.getUrl())
                        .thumbnail(entity.getThumbnail())
                        .isDeleted(entity.getIsDeleted())
                        //서비스에서 리스트 매핑 방법
//                        .List(entity.getTags)<>()
                        //아 서비스에서 리스트 매핑하는 방법을 모르겠음;;
                        //도저히 찾아봐도 안 나옴
                        .build())

                .orElseThrow(()-> new RuntimeException("Post Not Found"));
    }

    public PostDTO updatePost(PostDTO dto) {
        return postRepository.findById(dto.getId())
                .map(entity -> PostDTO.builder()
                        .id(entity.getId())
                        .isBest(entity.getIsBest()) //엥시발??
                        .title(entity.getTitle())
                        .content(entity.getContent())
                        .url(entity.getUrl())
                        .thumbnail(entity.getThumbnail())
                        .isDeleted(entity.getIsDeleted())
                        //서비스에서 리스트 매핑 방법
//                        .List(entity.getTags)<>()
                        //아 서비스에서 리스트 매핑하는 방법을 모르겠음;;
                        //도저히 찾아봐도 안 나옴
                        .build())

                .orElseThrow(()-> new RuntimeException("Post Not Found"));
    }
public PostDTO deletePost(Integer id) {
    return postRepository.findById(id)
            .map(entity -> PostDTO.builder()
                    .id(entity.getId())
                    .isBest(entity.getIsBest()) //엥시발??
                    .title(entity.getTitle())
                    .content(entity.getContent())
                    .url(entity.getUrl())
                    .thumbnail(entity.getThumbnail())
                    .isDeleted(entity.getIsDeleted())
                    //서비스에서 리스트 매핑 방법
//                        .List(entity.getTags)<>()
                    //아 서비스에서 리스트 매핑하는 방법을 모르겠음;;
                    //도저히 찾아봐도 안 나옴
                    .build())

            .orElseThrow(()-> new RuntimeException("Post Not Found"));


}
}
