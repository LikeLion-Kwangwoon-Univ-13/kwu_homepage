package Project.LIKELION.DTO.MEMBERS;

import java.util.List; //엔티티에서 원투매니 원투매니로 연결할건데 이게 dto에서도 필요한가?
import lombok.*;

@Getter
@Builder

public class StackDTO {
    private Integer id; //아이디
    private String name; //스택이름
}
