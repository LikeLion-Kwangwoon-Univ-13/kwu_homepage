package Project.LIKELION.controller;

import Project.LIKELION.DTO.POST.PostDTO;
import Project.LIKELION.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/blog")

public class PostController {
    private final PostService postService;

    @GetMapping("/{id}") //그냥 고정 경로를 쓰면 url에 어떤 id가 들어가는지 알 수 없음
    public ResponseEntity<PostDTO> getPostById(@PathVariable Integer id) {
        PostDTO dto = postService.getPostById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping //생성할땐 아직 id가 없음
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO dto) {
        PostDTO created = postService.createPost(dto);
        return ResponseEntity.ok(created);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable Integer id, @RequestBody PostDTO dto) {
        PostDTO updated = postService.updatePost(dto);
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<PostDTO> deletePost(@PathVariable Integer id) {
        PostDTO deleted = postService.deletePost(id);
        return ResponseEntity.ok(deleted);
    }
}
