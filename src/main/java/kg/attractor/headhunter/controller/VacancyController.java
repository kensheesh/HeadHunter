package kg.attractor.headhunter.controller;

import kg.attractor.headhunter.dto.ResumeDto;
import kg.attractor.headhunter.dto.VacancyDto;
import kg.attractor.headhunter.exception.ResumeNotFoundException;
import kg.attractor.headhunter.exception.VacancyNotFoundException;
import kg.attractor.headhunter.service.impl.VacancyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyServiceImpl vacancyService;

    @GetMapping("vacancies")
    public ResponseEntity<?> getVacancies() {
        return ResponseEntity.ok(vacancyService.getVacancies());

    }

    @GetMapping("vacancies/categoryId{categoryId}")
    public ResponseEntity<?> getVacanciesByCategory(@PathVariable int categoryId) {
        try {
            List<VacancyDto> vacancyDto = vacancyService.getVacanciesByCategory(categoryId);
            return ResponseEntity.ok(vacancyDto);
        } catch (VacancyNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("vacancies/userId{userId}")
    public ResponseEntity<?> getVacanciesByUserId(@PathVariable int userId) {
        try {
            List<VacancyDto> vacancyDto = vacancyService.getVacanciesByUserId(userId);
            return ResponseEntity.ok(vacancyDto);
        } catch (VacancyNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("vacancies/active")
    public ResponseEntity<?> getActiveVacancies() {
        return ResponseEntity.ok(vacancyService.getActiveVacancies());
    }

}
