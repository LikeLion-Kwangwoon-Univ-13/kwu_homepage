package Project.LIKELION.Service.POST;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import Project.LIKELION.Entity.POST.TagEntity;
import Project.LIKELION.Repository.POST.TagRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    @Override
    public TagEntity getOrCreate(String tag) {
        return tagRepository.findByName(tag) //이름으로 태그 조회
                //결과가 없으면 새로 만들어서 저장
                .orElseGet(() -> tagRepository.save(TagEntity.builder().name(tag).build()));
    }

    @Override
    public List<TagEntity> getTagByNmaes(List<String> tagNames){
        if(tagNames == null || tagNames.isEmpty()){
            return Collections.emptyList();
        }
        return tagRepository.findByNameIn(tagNames);
    }

    //태그 조회. 있는건 그대로 내보내고 없는건 새로 만들어서 출력
    @Override
    public List<TagEntity> getOrCreateTagsByNmaes(List<String> tagNames){
        List<TagEntity> existingTags = tagRepository.findByNameIn(tagNames);

        List<String> existingNames = existingTags.stream()
                .map(TagEntity::getName)
                .collect(Collectors.toList());

        List<TagEntity> tagsToCreate = new ArrayList<>();
        for (String tagName : tagNames) {
            if(!existingNames.contains(tagName)){
                tagsToCreate.add(TagEntity.builder().name(tagName).build());
            }
        }
        List<TagEntity> savedTags = tagRepository.saveAll(tagsToCreate);
        List<TagEntity> allTags = new ArrayList<>();
        allTags.addAll(savedTags);
        return allTags;
    }

    //태그 이름 수정 기존 -> 뉴
    @Override
    public List<TagEntity> updateTagNames(String oldName, String newName){
        TagEntity tag = tagRepository.findByName(oldName)
                .orElseThrow(()-> new RuntimeException("Tag not found" + oldName));
        TagEntity newTag = TagEntity.builder().name(newName).build();
        tagRepository.delete(tag);
        TagEntity saved = tagRepository.save(newTag);

        List<TagEntity> result = new ArrayList<>();
        result.add(saved);
        return result;

    }
    //태그 여러 삭제
    @Override
    public List<TagEntity> deleteTagNames(List<String> tagNames){
        List<TagEntity> tagsToDelete = tagRepository.findByNameIn(tagNames);
        tagRepository.deleteAll(tagsToDelete);
        List<TagEntity> deletedTags = new ArrayList<>();
        for (TagEntity tag : tagsToDelete) {
            deletedTags.add(tag);
        }
        return deletedTags;
    }
    //단일 태그 삭제
    @Override
    public TagEntity deleteTag(String tagName){
        TagEntity tag = tagRepository.findByName(tagName)
                .orElseThrow(()-> new RuntimeException("Tag not found" + tagName));
        tagRepository.delete(tag);
        return tag;
    }
}
