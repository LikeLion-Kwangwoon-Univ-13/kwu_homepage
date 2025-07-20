//package Project.LIKELION.controller;
//
//import Project.LIKELION.DTO.MEMBERS.MembersDTO;
//import Project.LIKELION.DTO.RecruitDTO;
//import Project.LIKELION.Service.MemberService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/member")
//public class MemberController {
//    private final MemberService memberService;
//
//    @GetMapping("/{id}")
//    public ResponseEntity<MembersDTO> getMember(@PathVariable Integer id) {
//        MembersDTO dto = memberService.getMemberDetail(id);
//        return ResponseEntity.ok(dto);
//    }
//
//    @PostMapping
//    public ResponseEntity<MembersDTO> createMember(@RequestBody MembersDTO dto) {
//        MembersDTO created = memberService.createMember(dto);
//        return ResponseEntity.ok(created);
//    }
//
//    @PatchMapping("/{id}")
//    public ResponseEntity<MembersDTO> updateMember(@PathVariable Integer id, @RequestBody MembersDTO dto) {
//        MembersDTO updated = memberService.updateMember(dto);
//        return ResponseEntity.ok(updated);
//    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<MembersDTO> deleteMember(@PathVariable Integer id) {
//        memberService.deleteMember(id);
//        return ResponseEntity.noContent().build();
//
//    }
//}
