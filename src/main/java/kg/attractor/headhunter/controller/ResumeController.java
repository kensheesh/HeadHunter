package kg.attractor.headhunter.controller;

import kg.attractor.headhunter.dto.ResumeDto;
import kg.attractor.headhunter.exception.ResumeNotFoundException;
import kg.attractor.headhunter.exception.UserNotFoundException;
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

    @GetMapping
    public ResponseEntity<?> getResumes(@RequestParam int userId) {
        try {
            return ResponseEntity.ok(resumeService.getResumes(userId));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getResumeById(@PathVariable int id) {
        try {
            ResumeDto resumeDto = resumeService.getResumeById(id);
            return ResponseEntity.ok(resumeDto);
        } catch (ResumeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<?> getResumesByTitle(@PathVariable String title,
                                               @RequestParam int userId) {
        try {
            List<ResumeDto> resumeDto = resumeService.getResumesByTitle(title, userId);
            return ResponseEntity.ok(resumeDto);
        } catch (ResumeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }



    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getResumesByUserId(@PathVariable int userId) {
        try {
            List<ResumeDto> resumeDto = resumeService.getResumesByUserId(userId);
            return ResponseEntity.ok(resumeDto);
        } catch (ResumeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/categoryId/{categoryId}")
    public ResponseEntity<?> getResumesByCategoryId(@PathVariable(required = false) Integer categoryId) {
        if (categoryId == null || categoryId <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can't find resume with this category: " + categoryId);
        }
        try {
            List<ResumeDto> resumeDto = resumeService.getResumesByCategoryId(categoryId);
            return ResponseEntity.ok(resumeDto);
        } catch (ResumeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/categoryName/{categoryName}")
    public ResponseEntity<?> getResumesByCategoryName(@PathVariable String categoryName,
                                                      @RequestParam int userId) {
        if (categoryName == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can't find resume with this category: " + categoryName);
        }
        try {
            List<ResumeDto> resumeDto = resumeService.getResumesByCategoryName(categoryName, userId);
            return ResponseEntity.ok(resumeDto);
        } catch (ResumeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/status")
    public ResponseEntity<?> getActiveVacancies() {
        return ResponseEntity.ok(resumeService.getActiveResumes());
    }

    @GetMapping("/status/{userId}")
    public ResponseEntity<?> getActiveResumesByUserId(@PathVariable int userId) {
        try {
            List<ResumeDto> resumeDto = resumeService.getActiveResumesByUserId(userId);
            return ResponseEntity.ok(resumeDto);
        } catch (ResumeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createResume(@RequestBody ResumeDto resumeDto,
                                          @RequestParam int userId) {
        try {
            resumeService.createResume(resumeDto, userId);
            return ResponseEntity.ok().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editResume(@RequestBody ResumeDto resumeDto,
                                        @RequestParam int userId) {
        try {
            resumeService.editResume(resumeDto, userId);
            return ResponseEntity.ok().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteResumeById(@PathVariable int id,
                                              @RequestParam int userId) {
        try {
            resumeService.deleteResumeById(id, userId);
            return ResponseEntity.ok().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
