//package kg.attractor.headhunter.controller;
//
//import kg.attractor.headhunter.dto.VacancyDto;
//import kg.attractor.headhunter.exception.UserNotFoundException;
//import kg.attractor.headhunter.exception.VacancyNotFoundException;
//import kg.attractor.headhunter.service.impl.VacancyServiceImpl;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/vacancies")
//@RequiredArgsConstructor
//public class VacancyController {
//    private final VacancyServiceImpl vacancyService;
//
//    @GetMapping
//    public ResponseEntity<?> getVacancies(@RequestParam int userId) {
//        try {
//            return ResponseEntity.ok(vacancyService.getVacancies(userId));
//        } catch (UserNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<VacancyDto> getVacancyById(@PathVariable int id) {
//        try {
//            VacancyDto vacancy = vacancyService.getVacancyById(id);
//            return ResponseEntity.ok(vacancy);
//        } catch (VacancyNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//    }
//
//    @GetMapping("/name/{name}")
//    public ResponseEntity<?> getVacanciesByName(@PathVariable String name) {
//        try {
//            List<VacancyDto> vacancies = vacancyService.getVacanciesByName(name);
//            return ResponseEntity.ok(vacancies);
//        } catch (VacancyNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
//    }
//
//    @GetMapping("/categoryId/{categoryId}")
//    public ResponseEntity<?> getVacanciesByCategoryId(@PathVariable int categoryId) {
//        try {
//            List<VacancyDto> vacancyDto = vacancyService.getVacanciesByCategoryId(categoryId);
//            return ResponseEntity.ok(vacancyDto);
//        } catch (VacancyNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
//    }
//
//    @GetMapping("/categoryName/{categoryName}")
//    public ResponseEntity<?> getVacanciesByCategoryName(@PathVariable String categoryName,
//                                                        @RequestParam int userId) {
//        try {
//            List<VacancyDto> vacancies = vacancyService.getVacanciesByCategoryName(categoryName, userId);
//            return ResponseEntity.ok(vacancies);
//        } catch (VacancyNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
//    }
//
//    @GetMapping("/userId/{userId}")
//    public ResponseEntity<?> getVacanciesByUserId(@PathVariable int userId) {
//        try {
//            List<VacancyDto> vacancyDto = vacancyService.getVacanciesByUserId(userId);
//            return ResponseEntity.ok(vacancyDto);
//        } catch (VacancyNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
//    }
//
//    @GetMapping("/status")
//    public ResponseEntity<?> getActiveVacancies() {
//        return ResponseEntity.ok(vacancyService.getActiveVacancies());
//    }
//
//    @GetMapping("/status/{userId}")
//    public ResponseEntity<?> getActiveVacanciesByUserId(@PathVariable int userId) {
//        try {
//            List<VacancyDto> vacancyDto = vacancyService.getActiveVacanciesByUserId(userId);
//            return ResponseEntity.ok(vacancyDto);
//        } catch (VacancyNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
//    }
//
//    @GetMapping("/salaryDesc")
//    public ResponseEntity<?> getVacanciesBySalaryDescending() {
//        return ResponseEntity.ok(vacancyService.getVacanciesBySalary(false));
//    }
//
//    @GetMapping("/salaryAsc")
//    public ResponseEntity<?> getVacanciesBySalaryAscending() {
//        return ResponseEntity.ok(vacancyService.getVacanciesBySalary(true));
//    }
//
//    @GetMapping("/updateTimeDesc")
//    public ResponseEntity<?> getVacanciesByUpdateTimeDescending() {
//        return ResponseEntity.ok(vacancyService.getVacanciesByUpdateTime(false));
//    }
//
//    @GetMapping("/updateTimeAsc")
//    public ResponseEntity<?> getVacanciesByUpdateTimeAscending() {
//        return ResponseEntity.ok(vacancyService.getVacanciesByUpdateTime(true));
//    }
//
//    @PostMapping
//    public ResponseEntity<?> createVacancy(@RequestBody VacancyDto vacancyDto,
//                                           @RequestParam int userId) {
//        try {
//            vacancyService.createVacancy(vacancyDto, userId);
//            return ResponseEntity.ok().build();
//        } catch (UserNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
//    }
//
//    @PutMapping
//    public ResponseEntity<?> editVacancy(@RequestBody VacancyDto vacancyDto,
//                                         @RequestParam int userId) {
//        try {
//            vacancyService.editVacancy(vacancyDto, userId);
//            return ResponseEntity.ok().build();
//        } catch (UserNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteVacancyById(@PathVariable int id,
//                                               @RequestParam int userId) {
//        try {
//            vacancyService.deleteVacancyById(id, userId);
//            return ResponseEntity.ok().build();
//        } catch (UserNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
//
//    }
//}