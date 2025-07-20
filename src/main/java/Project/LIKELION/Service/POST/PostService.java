package Project.LIKELION.Service.POST;

import Project.LIKELION.DTO.POST.PostListDTO;
import Project.LIKELION.DTO.POST.PostRequestDTO;
import Project.LIKELION.DTO.POST.PostResponseDTO;

import java.util.List;
    public interface PostService {

        //전체 포스트 조회 기능 (블로그홈 api)
        PostListDTO getAllPosts(int cursor, int limit);

        //단일 조회 (블로그홈 api)
        PostRequestDTO getPostById(Integer id);

        //포스트 추가 기능 (포스트 추가 api)
        void createPost(PostRequestDTO post);

        //포스트 수정 기능
        void updatePost(PostRequestDTO post, Integer id);

        //포스트 삭제 기능
        void deletePost(Integer id);

        //포스트 베스트 선정 기능 (베스트 선정 api)
        void markPostAsBest(Integer id);

        List<PostResponseDTO> getCursor(int cursor, int limit);

        //포스트 베스트 추가 기능
        void createBestPost(Integer id);

        //포스트 베스트 수정(취소) 기능 (베스트 취소 api)
        void updateBestPost(Integer id);

        //추가 포스트 베스트 삭제 기능
        void deleteBestPost(Integer id);

        List<PostResponseDTO> getBestPosts();
    }
