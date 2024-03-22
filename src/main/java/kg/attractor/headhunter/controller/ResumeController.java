package kg.attractor.headhunter.controller;

import jakarta.validation.Valid;
import kg.attractor.headhunter.dto.ResumeCreateDto;
import kg.attractor.headhunter.dto.ResumeDto;
import kg.attractor.headhunter.dto.ResumeEditDto;
import kg.attractor.headhunter.exception.*;
import kg.attractor.headhunter.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resumes")
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;

    @PostMapping
    public ResponseEntity<?> createResumeForUserId(@RequestBody @Valid ResumeCreateDto resumeDto,
                                                   @RequestParam int userId) {
        try {
            resumeService.createResume(resumeDto, userId);
            return ResponseEntity.ok().build();
        } catch (UserNotFoundException |
                 CategoryNotFoundException |
                 ResumeNotFoundException |
                 WorkExperienceNotFoundException |
                 EducationInfoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editResumeForUserId(@RequestBody ResumeEditDto resumeDto,
                                                 @RequestParam int userId) {
        try {
            resumeService.editResume(resumeDto, userId);
            return ResponseEntity.ok().build();
        } catch (UserNotFoundException | ResumeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllResumesForUserId(@RequestParam int userId) {
        return ResponseEntity.ok(resumeService.getUsersAllResumes(userId));
    }


    @GetMapping("/active")
    public ResponseEntity<?> getActiveResumesForUserId(@RequestParam int userId) {
        try {
            return ResponseEntity.ok(resumeService.getUsersActiveResumes(userId));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (ResumeNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/title/{name}")
    public ResponseEntity<?> getResumeByTitleForUserId(@PathVariable String name,
                                                       @RequestParam int userId) {
        try {
            List<ResumeDto> resumeDto = resumeService.getUsersResumeByTitle(name, userId);
            return ResponseEntity.ok(resumeDto);
        } catch (ResumeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/categoryName/{categoryName}")
    public ResponseEntity<?> getResumesByCategoryNameForUserId(@PathVariable String categoryName,
                                                               @RequestParam int userId) {
        if (categoryName == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can't find resume with this category: " + categoryName);
        }
        try {
            List<ResumeDto> resumeDto = resumeService.getUsersResumesByCategoryName(categoryName, userId);
            return ResponseEntity.ok(resumeDto);
        } catch (ResumeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteResumeByIdForUserid(@PathVariable int id,
                                                       @RequestParam int userId) {
        try {
            resumeService.deleteResumeById(id, userId);
            return ResponseEntity.ok().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}