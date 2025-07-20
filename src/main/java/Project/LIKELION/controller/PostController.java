package Project.LIKELION.controller;

import Project.LIKELION.DTO.POST.PostRequestDTO;
import Project.LIKELION.DTO.POST.PostResponseDTO;
import Project.LIKELION.DTO.POST.PostListDTO;
import Project.LIKELION.Service.POST.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Slf4j
public class PostController {
    private final PostService postService;

    //블로그 홈
    @GetMapping("/blog")
    public ResponseEntity<?> getAllPosts() {
        try {
            List<PostResponseDTO> allPosts = postService.getCursor(0, 10); // 전체에서 10개
            List<PostResponseDTO> bestPosts = postService.getBestPosts();  // ⭐ 베스트 불러오기

            PostListDTO result = PostListDTO.builder()
                    .posts(allPosts)
                    .best(bestPosts)
                    .build();

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ERROR : 해당 정보를 찾을 수 없습니다.");
        }
    }


    //블로그 포스트
    @GetMapping("/blog/posts")
    public ResponseEntity<?> getPaginatedPosts(
            @RequestParam(name = "cursor", defaultValue = "0") int cursor,
            @RequestParam(name = "limit", defaultValue = "10") int limit) {

        try {
            List<PostResponseDTO> allPosts = postService.getCursor(cursor, limit);

            if (allPosts == null || allPosts.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("ERROR", "해당 정보를 찾을 수 없습니다."));
            }
            List<PostResponseDTO> bestPosts = postService.getBestPosts();
            // best 필드는 null 고정 (지금은)
            PostListDTO result = PostListDTO.builder()
                    .posts(allPosts)
                    .best(bestPosts)
                    .build();

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("ERROR", "서버 오류가 발생했습니다."));
        }

    }


    //블로그 포스트 추가
    @PostMapping("/manage/newpost")//생성할땐 아직 id가 없음
    public ResponseEntity<?> createPost(@RequestBody PostRequestDTO dto) {
        try{
            log.warn("PostDTO = {}", dto);
            postService.createPost(dto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("msg", "성공적으로 포스트를 추가하였습니다."));
        } catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("ERROR","포스트 추가를 실패하였습니다."));
        }

    }

    //블로그 베스트 선정
    @PatchMapping("/manage/posts/{id}/setBest")
    public ResponseEntity<?> setBest(@PathVariable Integer id) {
        try{
            postService.markPostAsBest(id);
            return ResponseEntity.ok(
                    Map.of("msg", " 베스트 블로그 설정이 완료되었습니다.")
            );
        } catch (IllegalStateException ex){
            return ResponseEntity.badRequest().body(
                    Map.of("ERROR", "이미 선정된 베스트 게시글 입니다.")
            );
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of("ERROR", "해당 정보를 찾을 수 없습니다.")
            );
        }
    }

    //블로그 베스트 취소
    @PatchMapping("/manage/posts/{id}/cancelBest")
    public ResponseEntity<?> cancelBest(@PathVariable Integer id) {
        try{
            postService.updateBestPost(id);
            return ResponseEntity.ok(Map.of("msg", "베스트 게시글 취소가 완료되었습니다"));
        } catch(IllegalStateException ex){
            return ResponseEntity.badRequest().body(Map.of("ERROR", "기존 베스트 게시글이 아닙니다"));
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("ERROR", "해당 정보를 찾을 수 없습니다."));
        }
    }

    //블로그 포스트 삭제
    @DeleteMapping("/manage/posts/{id}/delete")
    public ResponseEntity<?> deletePost(@PathVariable Integer id) {
        try{
            postService.deletePost(id);
            return ResponseEntity.ok(Map.of("msg", "삭제되었습니다"));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("ERROR", "해당 게시글의 정보를 찾을 수 없습니다"));
        }
    }

    //블로그 수정
    @PatchMapping("/manage/posts/newpost/{id}")
    public ResponseEntity<?> updatePost(@RequestBody PostRequestDTO dto,@PathVariable("id") Integer id) {
        try{
            postService.updatePost(dto,id);
            return ResponseEntity.ok(Map.of("msg", "블로그 수정이 완료되었습니다."));
        } catch(IllegalStateException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "해당 정보를 찾을 수 없습니다."));
        }
    }
}
