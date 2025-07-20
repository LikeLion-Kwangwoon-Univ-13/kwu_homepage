//package Project.LIKELION.Service;
//
//import Project.LIKELION.DTO.MEMBERS.GenerationDTO;
//import Project.LIKELION.DTO.MEMBERS.MembersDTO;
//import Project.LIKELION.DTO.MEMBERS.StackDTO;
//import Project.LIKELION.Entity.MEMBERS.MembersEntity;
//import Project.LIKELION.Repository.MEMBERS.MembersRepository;
//import Project.LIKELION.Repository.MEMBERS.StacksRepository;
//import Project.LIKELION.Repository.MEMBERS.GenerationRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class MemberService {
//
//    private final MembersRepository    membersRepository;
//    private final GenerationRepository generationRepository;
//    private final StacksRepository     stacksRepository;
//
//    //단일 멤버 조회
//    @Transactional(readOnly = true)
//    public MembersDTO getMemberDetail(Integer id) {
//        MembersEntity e = membersRepository.findById(id)
//                .orElseThrow(() -> new ResponseStatusException(
//                        HttpStatus.NOT_FOUND, "멤버가 없습니다. id=" + id
//                ));
//        return mapToDTO(e);
//    }
//
//    //멤버 생성
//    @Transactional
//    public MembersDTO createMember(MembersDTO dto) {
//        MembersEntity member = MembersEntity.builder()
//                .generation_id(dto.getGeneration_id())
//                .profile(dto.getProfile())
//                .name(dto.getName())
//                .position(dto.getPosition())
//                .part(dto.getPart())
//                .github(dto.getGithub())
//                .instagram(dto.getInstagram())
//                .created_at(dto.getCreated_at() != null
//                        ? dto.getCreated_at()
//                        : LocalDate.now()
//                )
//                .build();
//        member = membersRepository.save(member);
//
//        return mapToDTO(member);
//    }
//
//    //멤버 수정
//    @Transactional
//    public MembersDTO updateMember(MembersDTO dto) {
//        MembersEntity member = membersRepository.findById(dto.getId())
//                .orElseThrow(() -> new ResponseStatusException(
//                        HttpStatus.NOT_FOUND, "멤버가 없습니다. id=" + dto.getId()
//                ));
//
//        member.getMembers_stacks().clear();
//        return mapToDTO(member);
//    }
//
//    // 멤버 삭제
//    @Transactional
//    public void deleteMember(Integer id) {
//        if (!membersRepository.existsById(id)) {
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, "멤버가 없습니다. id=" + id
//            );
//        }
//        membersRepository.deleteById(id);
//    }
//
//    //Entity->DTO
//    private MembersDTO mapToDTO(MembersEntity membersEntity) {
//        GenerationDTO gen = GenerationDTO.builder()
//                .id(membersEntity.getGeneration_id())
//                .build();
//
//        List<StackDTO> stacks = membersEntity.getMembers_stacks().stream()
//                .map(ms -> StackDTO.builder()
//                        .id(ms.getStack().getId())
//                        .name(ms.getStack().getName())
//                        .build()
//                )
//                .collect(Collectors.toList());
//
//        return MembersDTO.builder()
//                .id(membersEntity.getId())
//                .generation_id(gen.getId())
//                .profile(membersEntity.getProfile())
//                .name(membersEntity.getName())
//                .position(membersEntity.getPosition())
//                .part(membersEntity.getPart())
//                .github(membersEntity.getGithub())
//                .instagram(membersEntity.getInstagram())
//                .created_at(membersEntity.getCreated_at())
//                .stacks(stacks)
//                .build();
//    }
//}
