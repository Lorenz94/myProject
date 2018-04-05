package net.thumbtack.school.hiring.server.dao.daoimpl;

import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.response.vacancy.GetVacanciesDtoResponse;
import net.thumbtack.school.hiring.person.Employee;
import net.thumbtack.school.hiring.person.Employer;

import net.thumbtack.school.hiring.vacancy.Requirement;
import net.thumbtack.school.hiring.vacancy.Vacancy;

import java.util.Set;


public interface EmployerDao {

    void registerEmployer(String token, Employer employer) throws ServerException;
    void logOffEmployer(String token) throws ServerException;
    void logOnEmployer(String token) throws ServerException;
    void deleteEmployer(String token) throws ServerException;
    Employer getEmployer(String token) throws ServerException;
    void updateEmployer(String token, Employer employer) throws ServerException;
    void hireEmployee(String employeeToken, String employerToken, String vacancyName) throws ServerException;
    Set<Employee> getBestEmployees(Vacancy vacancy) throws ServerException;
    Set<Employee> getGoodEmployees(Vacancy vacancy) throws ServerException;
    Set<Employee> getAnyLevelEmployees(Vacancy vacancy) throws ServerException;
    Set<Employee> getAsLeastOneRequirementEmployees(Vacancy vacancy) throws ServerException;
    void addVacancy(String token, Vacancy vacancy) throws ServerException;
    GetVacanciesDtoResponse getVacancies(String token) throws ServerException;
    void removeVacancy(String token, String vacancyName) throws ServerException;
    void addVacancyRequirement(String token, String vacancy, Requirement requirement) throws ServerException;
    void removeVacancyRequirement(String token, String vacancy, Requirement requirement) throws ServerException;
    void setVacancyActivity(String token, String vacancy, boolean active) throws ServerException;

}
