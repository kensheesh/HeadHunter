package kg.attractor.headhunter.controller.api;

import jakarta.validation.Valid;
import kg.attractor.headhunter.dto.ResumeCreateDto;
import kg.attractor.headhunter.dto.ResumeDto;
import kg.attractor.headhunter.dto.ResumeEditDto;
import kg.attractor.headhunter.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resumes")
@RequiredArgsConstructor
public class ResumeRestController {
    private final ResumeService resumeService;

    @GetMapping
    public ResponseEntity<?> getAllActiveResumesByName(Authentication authentication) {
        List<ResumeDto> resumes = resumeService.getAllActiveResumes(authentication);
        return ResponseEntity.ok(resumes);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getAllActiveResumesByName(@PathVariable String name, Authentication authentication) {
        List<ResumeDto> resumes = resumeService.getAllResumesByName(name, authentication);
        return ResponseEntity.ok(resumes);
    }

    @GetMapping("/categoryName/{categoryName}")
    public ResponseEntity<?> getAllActiveResumesByCategoryName(@PathVariable String categoryName, Authentication authentication) {
        List<ResumeDto> resumes = resumeService.getAllResumesByCategoryName(categoryName, authentication);
        return ResponseEntity.ok(resumes);
    }

    @GetMapping("/applicant")
    public ResponseEntity<?> getAllResumesOfApplicant(Authentication authentication) {
        List<ResumeDto> resumes = resumeService.getAllResumesOfApplicant(authentication);
        return ResponseEntity.ok(resumes);
    }

//    @PostMapping
//    public ResponseEntity<?> createResumeForApplicant(@Valid @RequestBody ResumeCreateDto resumeDto, Authentication authentication) {
//        resumeService.createResumeForApplicant(resumeDto, authentication);
//        return ResponseEntity.ok("Resume is successfully created");
//    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editResumeForApplicant(@Valid @RequestBody ResumeEditDto resumeEditDto, Authentication authentication, @PathVariable Integer id) {
        resumeService.editResumeForApplicant(resumeEditDto, authentication, id);
        return ResponseEntity.ok("Resume is successfully edited");
    }

    @DeleteMapping("/{resumeId}")
    public ResponseEntity<?> deleteResumeByIdForUserid(@PathVariable int resumeId, Authentication authentication) {
        resumeService.deleteResumeById(resumeId, authentication);
        return ResponseEntity.ok("Resume is successfully deleted");
    }

}