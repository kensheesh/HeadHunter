package kg.attractor.headhunter.service;

import kg.attractor.headhunter.dto.VacancyCreateDto;
import kg.attractor.headhunter.dto.VacancyDto;
import kg.attractor.headhunter.dto.VacancyEditDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface VacancyService {

    List<VacancyDto> getAllActiveVacancies();

    List<VacancyDto> getAllActiveVacanciesByName(String title);

    List<VacancyDto> getAllActiveVacanciesByCategoryName(String categoriesName);

    List<VacancyDto> getAllVacanciesOfEmployer(Authentication authentication);

    void createVacancyForEmployer(VacancyCreateDto vacancyDto, Authentication authentication);

    void editVacancy(VacancyEditDto vacancyDto, Authentication authentication, Integer id);

    void deleteVacancyById(Integer vacancyId, Authentication authentication);

    List<VacancyDto> getVacanciesBySalary(boolean ascending);

    List<VacancyDto> getVacanciesByUpdateTime(boolean ascending);

}
