package net.thumbtack.school.hiring.server.dao;

import net.thumbtack.school.hiring.response.vacancy.GetVacanciesDtoResponse;
import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.person.Employee;
import net.thumbtack.school.hiring.person.Employer;
import net.thumbtack.school.hiring.server.dao.daoimpl.EmployerDao;
import net.thumbtack.school.hiring.vacancy.Requirement;
import net.thumbtack.school.hiring.vacancy.Vacancy;

import java.util.Set;


public class EmployerDaoImpl extends BaseDao implements EmployerDao {

    public EmployerDaoImpl() {
    }

    public void registerEmployer(String token, Employer employer) throws ServerException {
        dataBase.insertEmployer(token, employer);
    }

    public void logOffEmployer(String token) throws ServerException {
        dataBase.logOffEmployer(token);
    }

    public void logOnEmployer(String token) throws ServerException {
        dataBase.logOnEmployer(token);
    }

    public void deleteEmployer(String token) throws ServerException {
        dataBase.deleteEmployer(token);
    }

    public Employer getEmployer(String token) throws ServerException {
        return dataBase.getEmployer(token);
    }

    public void updateEmployer(String token, Employer employer) throws ServerException {
        dataBase.updateEmployer(token, employer);
    }

    public void hireEmployee(String employeeToken, String employerToken, String vacancyName) throws ServerException {
        dataBase.hireEmployee(employeeToken,employerToken,vacancyName);
    }

    public Set<Employee> getBestEmployees(Vacancy vacancy) throws ServerException {
        return dataBase.getBestEmployees(vacancy);
    }

    public Set<Employee> getGoodEmployees(Vacancy vacancy) throws ServerException {
        return dataBase.getGoodEmployees(vacancy);
    }

    public Set<Employee> getAnyLevelEmployees(Vacancy vacancy) throws ServerException {
        return dataBase.getAnyLevelEmployees(vacancy);
    }

    public Set<Employee> getAsLeastOneRequirementEmployees(Vacancy vacancy) throws ServerException {
        return dataBase.getAsLeastOneRequirementEmployees(vacancy);
    }

    public void addVacancy(String token, Vacancy vacancy) throws ServerException {
        dataBase.insertVacancy(token, vacancy);
    }

    public GetVacanciesDtoResponse getVacancies(String token) throws ServerException {
        return new GetVacanciesDtoResponse(dataBase.getEmployerVacancies(token));
    }

    public void removeVacancy(String token, String vacancyName) throws ServerException {
        dataBase.deleteVacancy(token, vacancyName);
    }

    public void updateVacancy(String token, Vacancy vacancy) throws ServerException {
        dataBase.updateVacancy(token, vacancy);
    }

    public void addVacancyRequirement(String token, String vacancyName, Requirement requirement) throws ServerException {
        dataBase.insertRequirement(token, vacancyName, requirement);
    }

    public void removeVacancyRequirement(String token, String vacancy, Requirement requirement) throws ServerException {
        dataBase.deleteRequirement(token, vacancy, requirement);
    }

    public void setVacancyActivity(String token, String vacancy, boolean active) throws ServerException {
        dataBase.updateVacancyActivity(token, vacancy, active);
    }

}
