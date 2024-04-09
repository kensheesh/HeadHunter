package kg.attractor.headhunter.service;

import kg.attractor.headhunter.dto.VacancyCreateDto;
import kg.attractor.headhunter.dto.VacancyDto;
import kg.attractor.headhunter.dto.VacancyEditDto;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface VacancyService {
    VacancyDto getVacancyById(Integer id);

    Page<VacancyDto> getAllActiveVacancies(int pageNumber, int pageSize);

    List<VacancyDto> getAllActiveVacanciesByName(String title);

    public Page<VacancyDto> getAllActiveVacanciesByCategoryName(String categoriesName, int pageNumber, int pageSize);

    List<VacancyDto> getAllVacanciesOfEmployer(Authentication authentication);
    List<VacancyDto> getAllVacanciesOfEmployerById(Integer userId);

    void createVacancyForEmployer(VacancyCreateDto vacancyDto, Integer userId);

    void editVacancy(VacancyEditDto vacancyDto, Integer id);

    void deleteVacancyById(Integer vacancyId, Authentication authentication);

    List<VacancyDto> getVacanciesBySalary(boolean ascending);

    List<VacancyDto> getVacanciesByUpdateTime(boolean ascending);

}
