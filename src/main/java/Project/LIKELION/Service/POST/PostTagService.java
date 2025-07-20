package Project.LIKELION.Service.POST;

import Project.LIKELION.Entity.POST.TagEntity;
import Project.LIKELION.Entity.POST.PostEntity;
import java.util.List;

public interface PostTagService {
    void linkTagsToPost(PostEntity post, List<TagEntity> tags);
    void linkTagToPost(PostEntity post, TagEntity tags);
    void unlinkTagFromPost(PostEntity post, TagEntity tags);

}
