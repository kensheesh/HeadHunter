package kg.attractor.headhunter.service;

import kg.attractor.headhunter.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface VacancyService {
    void updateVacancyUpdateTime(Integer id);

    VacancyDto getVacancyById(Integer id);

    VacancyViewEditDto getVacancyByIdForEdit(Integer id);

    Page<VacancyViewAllDto> getAllActiveVacancies(int pageNumber, int pageSize, String category,
                                                  String sortType, String sortDirection);
//    List<VacancyDto> getAllActiveVacanciesByName(String title);
//
//    List<VacancyDto> getAllActiveVacanciesByCategoryName(String categoriesName);
//
//    List<VacancyDto> getAllVacanciesOfEmployer(Authentication authentication);

    List<VacancyViewAllDto> getAllVacanciesOfEmployerById(Integer userId);

    void createVacancyForEmployer(VacancyCreateDto vacancyDto, Integer userId);

    void editVacancy(VacancyEditDto vacancyDto, Integer id);

    void deleteVacancyById(Integer vacancyId, Authentication authentication);

    Page<VacancyViewAllDto> getAllActiveVacanciesByUserId(Integer pageNumber, int pageSize, Integer id);

//    List<VacancyDto> getVacanciesBySalary(boolean ascending);
//
//    List<VacancyDto> getVacanciesByUpdateTime(boolean ascending);
//
}
