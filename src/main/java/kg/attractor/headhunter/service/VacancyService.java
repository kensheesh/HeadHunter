package kg.attractor.headhunter.service;

import kg.attractor.headhunter.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface VacancyService {
    VacancyDto getVacancyById(Integer id);
    VacancyViewEditDto getVacancyByIdForEdit(Integer id);

    Page<VacancyViewAllDto> getAllActiveVacancies(int pageNumber, int pageSize, String category);

    List<VacancyDto> getAllActiveVacanciesByName(String title);

    List<VacancyDto> getAllActiveVacanciesByCategoryName(String categoriesName);

    List<VacancyDto> getAllVacanciesOfEmployer(Authentication authentication);
    List<VacancyDto> getAllVacanciesOfEmployerById(Integer userId);

    void createVacancyForEmployer(VacancyCreateDto vacancyDto, Integer userId);

    void editVacancy(VacancyEditDto vacancyDto, Integer id);

    void deleteVacancyById(Integer vacancyId, Authentication authentication);

    List<VacancyDto> getVacanciesBySalary(boolean ascending);

    List<VacancyDto> getVacanciesByUpdateTime(boolean ascending);

}
