package Project.LIKELION.Converter;

import org.springframework.stereotype.Component;
import Project.LIKELION.DTO.POST.PostRequestDTO;
import Project.LIKELION.Entity.POST.PostEntity;
import org.springframework.core.convert.converter.Converter;


@Component //클래스를 빈으로 자동 등록해줌
//요청을 엔티티로 변환할 수 있어요!
//DTO -> Entity 변환 기능 수행
public class PostConverter implements Converter <PostRequestDTO, PostEntity> {

    @Override
    //안전성 보장, 가독성, 유지보수성 향상
    public PostEntity convert(PostRequestDTO source) {
        return PostEntity.builder()
                .isBest(false)
                .title(source.getTitle())
                .isDeleted(false)
                .content(source.getContent())
                .url(source.getUrl())
                .thumbnail(source.getThumbnail())
                .build();
    }
}
