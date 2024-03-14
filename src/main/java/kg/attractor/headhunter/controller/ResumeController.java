package kg.attractor.headhunter.controller;

import kg.attractor.headhunter.dto.ResumeDto;
import kg.attractor.headhunter.exception.ResumeNotFoundException;
import kg.attractor.headhunter.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;

    @GetMapping("resumes/categoryId{categoryId}")
    public ResponseEntity<?> getResumesByCategory(@PathVariable int categoryId) {
        try {
            List<ResumeDto> resumeDto = resumeService.getResumesByCategory(categoryId);
            return ResponseEntity.ok(resumeDto);
        } catch (ResumeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("resumes/id{id}")
    public ResponseEntity<?> getResumeById(@PathVariable int id) {
        try {
            ResumeDto resumeDto = resumeService.getResumeById(id);
            return ResponseEntity.ok(resumeDto);
        } catch (ResumeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("resumes/userId{userId}")
    public ResponseEntity<?> getResumesByUserId(@PathVariable int userId) {
        try {
            List<ResumeDto> resumeDto = resumeService.getResumeByUserId(userId);
            return ResponseEntity.ok(resumeDto);
        } catch (ResumeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }



    @PostMapping("resumes/add")
    public ResponseEntity<?> createResume(@RequestBody ResumeDto resumeDto) {
        return ResponseEntity.ok(resumeService.createResume(resumeDto));
    }
}
