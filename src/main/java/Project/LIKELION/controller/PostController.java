package Project.LIKELION.controller;

import Project.LIKELION.DTO.POST.PostDTO;
import Project.LIKELION.Service.PostService;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/post")

public class PostController {
    private final PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPost() {
        PostDTO dto = postService.getPost();

        return ResponseEntity.ok().body(dto);
    }
}
