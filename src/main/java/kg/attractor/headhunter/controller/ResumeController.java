package kg.attractor.headhunter.controller;

import kg.attractor.headhunter.dto.ResumeDto;
import kg.attractor.headhunter.exception.ResumeNotFoundException;
import kg.attractor.headhunter.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;

    @GetMapping("resumes/{categoryId}")
    public ResponseEntity<?> getResumesByCategory(@PathVariable int categoryId) {
        try {
            List<ResumeDto> resumeDto = resumeService.getResumesByCategory(categoryId);
            return ResponseEntity.ok(resumeDto);
        } catch (ResumeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
