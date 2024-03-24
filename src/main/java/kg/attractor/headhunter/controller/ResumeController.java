//package kg.attractor.headhunter.controller;
//
//import jakarta.validation.Valid;
//import kg.attractor.headhunter.dto.ResumeCreateDto;
//import kg.attractor.headhunter.dto.ResumeDto;
//import kg.attractor.headhunter.dto.ResumeEditDto;
//import kg.attractor.headhunter.service.ResumeService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/resumes")
//@RequiredArgsConstructor
//public class ResumeController {
//    private final ResumeService resumeService;
//
//    @PostMapping
//    public ResponseEntity<?> createResumeForUserId(@Valid @RequestBody ResumeCreateDto resumeDto,
//                                                   @RequestParam int userId) {
//        resumeService.createResume(resumeDto, userId);
//        return ResponseEntity.ok().build();
//    }
//
//    @PutMapping
//    public ResponseEntity<?> editResumeForUserId(@Valid @RequestBody ResumeEditDto resumeDto,
//                                                 @RequestParam int userId) {
//        resumeService.editResume(resumeDto, userId);
//        return ResponseEntity.ok().build();
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteResumeByIdForUserid(@PathVariable int id,
//                                                       @RequestParam int userId) {
//        resumeService.deleteResumeById(id, userId);
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping
//    public ResponseEntity<?> getAllResumesForUserId(@RequestParam int userId) {
//        return ResponseEntity.ok(resumeService.getUsersAllResumes(userId));
//    }
//
//    @GetMapping("/active")
//    public ResponseEntity<?> getActiveResumesForUserId(@RequestParam int userId) {
//        return ResponseEntity.ok(resumeService.getUsersActiveResumes(userId));
//    }
//
//    @GetMapping("/title/{name}")
//    public ResponseEntity<?> getResumeByTitleForUserId(@PathVariable String name,
//                                                       @RequestParam int userId) {
//        List<ResumeDto> resumeDto = resumeService.getUsersResumeByTitle(name, userId);
//        return ResponseEntity.ok(resumeDto);
//    }
//
//    @GetMapping("/categoryName/{categoryName}")
//    public ResponseEntity<?> getResumesByCategoryNameForUserId(@PathVariable String categoryName,
//                                                               @RequestParam int userId) {
//        List<ResumeDto> resumeDto = resumeService.getUsersResumesByCategoryName(categoryName, userId);
//        return ResponseEntity.ok(resumeDto);
//    }
//}