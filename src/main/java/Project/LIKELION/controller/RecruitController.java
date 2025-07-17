package Project.LIKELION.controller;

import Project.LIKELION.DTO.RecruitDTO;
import Project.LIKELION.Service.RecruitService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/recruit")

public class RecruitController {
    private final RecruitService recruitService;

    @GetMapping
    public ResponseEntity<?> getRecruit() {
        try {
            RecruitDTO dto = recruitService.getRecruit(); //겟으로 조회기능
            return ResponseEntity.ok().body(dto);
        } catch (ResponseStatusException ex) {
            Map<String, String> error = new HashMap<>();
            error.put("서버 오류", ex.getReason());
            return ResponseEntity.status(ex.getStatusCode())
                    .body(error);
        }
    }

    @PostMapping
    public ResponseEntity<?/*반환값*/> createRecruit(@RequestBody RecruitDTO dto) {
        try {
            recruitService.createRecruit(dto);
            return ResponseEntity.ok("모집 일정이 등록되었습니다.");
        } catch(ResponseStatusException ex){
            Map<String, String> error = new HashMap<>();

            if(ex.getStatusCode() == HttpStatus.BAD_REQUEST){
                error.put("생성 오류", ex.getReason());
            }
            else if(ex.getStatusCode() == HttpStatus.NOT_FOUND){
                error.put("서버 오류", ex.getReason());
            }
            return ResponseEntity.status(ex.getStatusCode())
                    .body(error);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateRecruit(@PathVariable Integer id, @RequestBody RecruitDTO dto) {
        try {
            recruitService.updateRecruit(id, dto);
            return ResponseEntity.ok("모집 일정이 수정되었습니다.");
        } catch(ResponseStatusException ex){
            Map<String, String> error = new HashMap<>();
            if(ex.getStatusCode() == HttpStatus.BAD_REQUEST){
                error.put("수정 실패", ex.getReason());
            }
            else if(ex.getStatusCode() == HttpStatus.NOT_FOUND){
                error.put("서버 오류", ex.getReason());
            }
            return ResponseEntity.status(ex.getStatusCode())
                    .body(error);
        }
    }
}
