package Project.LIKELION.Service.POST;
import Project.LIKELION.Entity.POST.TagEntity;
import java.util.List;

public interface TagService {
    //단일 태그
    TagEntity getOrCreate(String tag);
    //조회해서 있는 것만 반환. 나머지는 무시.
    List<TagEntity> getTagByNmaes(List<String> tagNames);
    //다중 태그
    List<TagEntity> getOrCreateTagsByNmaes(List<String> tagNames);
    //태그 이름 수정 (기존 -> 뉴)
    List<TagEntity> updateTagNames(String oldName, String newName);
    //태그 여러 삭제
    List<TagEntity> deleteTagNames(List<String> tagNames);
    //단일 태그 삭제
    TagEntity deleteTag(String tagName);

}
