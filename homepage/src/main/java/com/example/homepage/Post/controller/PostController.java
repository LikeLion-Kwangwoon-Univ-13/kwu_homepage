package com.example.homepage.Post.controller;

import com.example.homepage.Post.dto.PostRequestDTO;
import com.example.homepage.Post.service.PostService;
import com.example.homepage.Post.dto.PostResponseListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping(value = "/blog")
    public ResponseEntity<?> getHomePosts()
    {
        try{
            return ResponseEntity.ok(postService.getHome());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR : 해당 정보를 찾을 수 없습니다.");
        }
    }

    @GetMapping(value = "/blog/posts")
    public ResponseEntity<?> getCursorPosts(
            @RequestParam("cursor") int cursor,
            @RequestParam("limit") int limit
    ){
        try{
            PostResponseListDTO response = postService.getCursor(cursor, limit);
            return ResponseEntity.ok(response);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR : 해당 정보를 찾을 수 없습니다.");
        }
    }
    
    @PostMapping(value = "/manage/newpost")
    public ResponseEntity<?> createNewPost(
            @RequestBody PostRequestDTO postRequestDTO
    ){
        try{
            postService.createPost(postRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("msg", "성공적으로 포스트를 추가하였습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("ERROR : 포스트 추가를 실패하였습니다.");
        }
    }

    @PatchMapping(value = "/manage/posts/newpost/{id}")
    public ResponseEntity<?> updatePost(
            @RequestBody PostRequestDTO postRequestDTO,
            @PathVariable("id") Long id
    ){
        try{
            postService.updatePost(id, postRequestDTO);
            return ResponseEntity.ok(Map.of("msg", "블로그 수정이 완료되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "해당 정보를 찾을 수 없습니다."));
        }
    }

    @DeleteMapping(value = "/manage/posts/{id}/delete")
    public ResponseEntity<?> deletePost(
            @PathVariable("id") Long id
    ){
        try{
            postService.deletePost(id);
            return ResponseEntity.ok(Map.of("msg", "삭제가 완료되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "해당 정보를 찾을 수 없습니다."));
        }
    }

    @PatchMapping(value = "/manage/posts/{id}/setBest")
    public ResponseEntity<?> setBestPost(@PathVariable("id") Long id){
        try{
            postService.setPostBest(id);
            return ResponseEntity.ok(Map.of("msg", "베스트 블로그 설정이 완료되었습니다."));
        }
        catch(IllegalStateException e){
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "이미 베스트 게시글입니다."));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "해당 정보를 찾을 수 없습니다."));
        }
    }

    @PatchMapping(value = "/manage/posts/{id}/cancelBest")
    public ResponseEntity<?> cancelBestPost(@PathVariable("id") Long id){
        try{
            postService.cancelPostBest(id);
            return ResponseEntity.ok(Map.of("msg", "베스트 블로그 해제가 완료되었습니다."));
        } catch(IllegalStateException e) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "이미 베스트 게시글이 아닙니다."));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "해당 정보를 찾을 수 없습니다."));
        }
    }
}
