package kg.attractor.headhunter.controller;

import jakarta.validation.Valid;
import kg.attractor.headhunter.dto.VacancyCreateDto;
import kg.attractor.headhunter.dto.VacancyEditDto;
import kg.attractor.headhunter.service.impl.VacancyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vacancies")
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyServiceImpl vacancyService;

    @GetMapping
    public ResponseEntity<?> getAllActiveVacancies() {
        return ResponseEntity.ok(vacancyService.getAllActiveVacancies());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getActiveVacanciesByName(@PathVariable String name) {
        return ResponseEntity.ok(vacancyService.getAllActiveVacanciesByName(name));
    }

    @GetMapping("/categoryName/{categoryName}")
    public ResponseEntity<?> getVacanciesByCategoryId(@PathVariable String categoryName) {
        return ResponseEntity.ok(vacancyService.getAllActiveVacanciesByCategoryName(categoryName));
    }

    @GetMapping("/employer")
    public ResponseEntity<?> getVacanciesByCategoryId(Authentication authentication) {
        return ResponseEntity.ok(vacancyService.getAllVacanciesOfEmployer(authentication));
    }

    @PostMapping
    public ResponseEntity<?> createVacancy(@Valid @RequestBody VacancyCreateDto vacancyDto, Authentication authentication) {
        vacancyService.createVacancyForEmployer(vacancyDto, authentication);
        return ResponseEntity.ok("Vacancy is created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editVacancyForEmployer(@Valid @RequestBody VacancyEditDto vacancyEditDto, Authentication authentication, @PathVariable Integer id) {
        vacancyService.editVacancy(vacancyEditDto, authentication, id);
        return ResponseEntity.ok("Vacancy is successfully edited");
    }

    @DeleteMapping("/{vacancyId}")
    public ResponseEntity<?> deleteVacancyById(@PathVariable Integer vacancyId, Authentication authentication) {
        vacancyService.deleteVacancyById(vacancyId, authentication);
        return ResponseEntity.ok("Vacancy is successfully deleted");

    }

    @GetMapping("/salaryDesc")
    public ResponseEntity<?> getVacanciesBySalaryDescending() {
        return ResponseEntity.ok(vacancyService.getVacanciesBySalary(false));
    }

    @GetMapping("/salaryAsc")
    public ResponseEntity<?> getVacanciesBySalaryAscending() {
        return ResponseEntity.ok(vacancyService.getVacanciesBySalary(true));
    }

    @GetMapping("/updateTimeDesc")
    public ResponseEntity<?> getVacanciesByUpdateTimeDescending() {
        return ResponseEntity.ok(vacancyService.getVacanciesByUpdateTime(false));
    }

    @GetMapping("/updateTimeAsc")
    public ResponseEntity<?> getVacanciesByUpdateTimeAscending() {
        return ResponseEntity.ok(vacancyService.getVacanciesByUpdateTime(true));
    }
}