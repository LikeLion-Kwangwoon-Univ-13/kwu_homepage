package Project.LIKELION.controller;

import Project.LIKELION.DTO.POST.PostDTO;
import Project.LIKELION.Service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/blog")

public class PostController {
    private final PostService postService;

    @GetMapping("/get")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Integer id) { //매핑된 tag엔티티 포함해서 모든 포스트 엔티티를 반환하겠다
        PostDTO dto = postService.getPostById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/post")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO dto) {
        PostDTO created = postService.createPost(dto);
        return ResponseEntity.ok(created);
    }
    @PatchMapping("/patch")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO dto) {
        PostDTO updated = postService.updatePost(dto);
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<PostDTO> deletePost(@PathVariable Integer id) {
        PostDTO deleted = postService.deletePost(id);
        return ResponseEntity.ok(deleted);
    }

}
