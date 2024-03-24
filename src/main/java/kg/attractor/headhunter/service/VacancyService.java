//package kg.attractor.headhunter.service;
//
//import kg.attractor.headhunter.dto.VacancyDto;
//import kg.attractor.headhunter.exception.UserNotFoundException;
//import kg.attractor.headhunter.exception.VacancyNotFoundException;
//
//import java.util.List;
//
//public interface VacancyService {
//    List<VacancyDto> getVacancies(int userId) throws UserNotFoundException;
//
//    VacancyDto getVacancyById(int id) throws VacancyNotFoundException;
//
//    List<VacancyDto> getVacanciesByName(String name) throws VacancyNotFoundException;
//
//    List<VacancyDto> getVacanciesByCategoryId(int categoryId) throws VacancyNotFoundException;
//
//    List<VacancyDto> getVacanciesByCategoryName(String categoryName, int userId) throws VacancyNotFoundException;
//
//    List<VacancyDto> getVacanciesByUserId(int userId) throws VacancyNotFoundException;
//
//    List<VacancyDto> getActiveVacancies();
//
//    List<VacancyDto> getActiveVacanciesByUserId(int userId) throws VacancyNotFoundException;
//
//    List<VacancyDto> getVacanciesBySalary(boolean bool);
//
//    List<VacancyDto> getVacanciesByUpdateTime(boolean bool);
//
//    void createVacancy(VacancyDto vacancyDto, int userId) throws UserNotFoundException;
//
//    void editVacancy(VacancyDto vacancyDto, int userId) throws UserNotFoundException;
//
//    void deleteVacancyById(int id, int userId) throws UserNotFoundException;
//
//}
