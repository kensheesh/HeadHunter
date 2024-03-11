package kg.attractor.headhunter.service.impl;

import kg.attractor.headhunter.dao.VacancyDao;
import kg.attractor.headhunter.dto.VacancyDto;
import kg.attractor.headhunter.exception.VacancyNotFoundException;
import kg.attractor.headhunter.model.Vacancy;
import kg.attractor.headhunter.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {
    private final VacancyDao vacancyDao;

    @Override
    public List<VacancyDto> getVacancies() {
        List<Vacancy> vacancies = vacancyDao.getVacancies();
        List<VacancyDto> dtos = new ArrayList<>();
        vacancies.forEach(e -> dtos.add(VacancyDto.builder()
                .id(e.getId())
                .name(e.getName())
                .description(e.getDescription())
                .categoryId(e.getCategoryId())
                .salary(e.getSalary())
                .experienceFrom(e.getExperienceFrom())
                .experienceTo(e.getExperienceTo())
                .isActive(e.isActive())
                .authorId(e.getAuthorId())
                .createdDate(e.getCreatedDate())
                .updateTime(e.getUpdateTime())
                .build()));
        return dtos;
    }

    @Override
    public List<VacancyDto> getVacanciesByCategory(int categoryId) throws VacancyNotFoundException {
        List<Vacancy> vacancies = vacancyDao.getVacanciesByCategory(categoryId).orElseThrow(() -> new VacancyNotFoundException("Can't find vacancy with this category: " + categoryId));
        List<VacancyDto> dtos = new ArrayList<>();
        vacancies.forEach(e -> dtos.add(VacancyDto.builder()
                .id(e.getId())
                .name(e.getName())
                .description(e.getDescription())
                .categoryId(e.getCategoryId())
                .salary(e.getSalary())
                .experienceFrom(e.getExperienceFrom())
                .experienceTo(e.getExperienceTo())
                .isActive(e.isActive())
                .authorId(e.getAuthorId())
                .createdDate(e.getCreatedDate())
                .updateTime(e.getUpdateTime())
                .build()));
        return dtos;
    }
}