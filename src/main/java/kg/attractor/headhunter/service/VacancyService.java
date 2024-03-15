package kg.attractor.headhunter.service;

import kg.attractor.headhunter.dto.VacancyDto;
import kg.attractor.headhunter.exception.VacancyNotFoundException;

import java.util.List;

public interface VacancyService {
    List<VacancyDto> getVacancies();

    List<VacancyDto> getVacanciesByCategory(int categoryId) throws VacancyNotFoundException;

    List<VacancyDto> getVacanciesByUserId(int userId) throws VacancyNotFoundException;

    List<VacancyDto> getActiveVacancies();

    List<VacancyDto> getVacanciesBySalary(boolean bool);
    List<VacancyDto> getVacanciesByUpdateTime(boolean bool);

}
