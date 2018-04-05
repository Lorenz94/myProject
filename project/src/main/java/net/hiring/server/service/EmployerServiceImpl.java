package net.thumbtack.school.hiring.server.service;


import net.thumbtack.school.hiring.request.employer.HireEmployeeDtoRequest;
import net.thumbtack.school.hiring.response.employee.GetAnyLevelEmployeesDtoResponse;
import net.thumbtack.school.hiring.response.employee.GetBestEmployeesDtoResponse;
import net.thumbtack.school.hiring.response.employee.GetGoodEmployeesDtoResponse;
import net.thumbtack.school.hiring.response.employee.GetOneRequirementEmployeesDtoResponse;
import net.thumbtack.school.hiring.response.employer.GetEmployerDtoResponse;
import net.thumbtack.school.hiring.person.Employee;
import net.thumbtack.school.hiring.server.dao.EmployerDaoImpl;
import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.request.employer.DeleteEmployerDtoRequest;
import net.thumbtack.school.hiring.request.employer.UpdateEmployerDtoRequest;
import net.thumbtack.school.hiring.request.vacancy.*;
import net.thumbtack.school.hiring.person.Employer;
import net.thumbtack.school.hiring.request.employer.RegisterEmployerDtoRequest;
import net.thumbtack.school.hiring.server.service.serviceimpl.EmployerService;
import net.thumbtack.school.hiring.vacancy.Requirement;
import net.thumbtack.school.hiring.vacancy.Vacancy;

import java.util.Set;

public class EmployerServiceImpl extends ServiceUtil implements EmployerService {

    private EmployerDaoImpl dao;

    public EmployerServiceImpl() {
        dao = new EmployerDaoImpl();
    }

    public String registerEmployer(String requestJsonString) throws ServerException {
        RegisterEmployerDtoRequest registerEmployerDTORequest = getClassInstanceFromJson(requestJsonString, RegisterEmployerDtoRequest.class);
        String token = createToken();
        Employer employer = createEmployer(registerEmployerDTORequest);
        dao.registerEmployer(token, employer);
        return gson.toJson(token);
    }

    public String logOffEmployer(String requestJsonString) throws ServerException {
        dao.logOffEmployer(getTokenFromJson(requestJsonString));
        return gson.toJson(OK_MESSAGE);
    }

    public String logOnEmployer(String requestJsonString) throws ServerException {
        dao.logOnEmployer(getTokenFromJson(requestJsonString));
        return gson.toJson(OK_MESSAGE);
    }

    public String deleteEmployer(String requestJsonString) throws ServerException {
        DeleteEmployerDtoRequest deleteEmployerDtoRequest = getClassInstanceFromJson(requestJsonString, DeleteEmployerDtoRequest.class);
        dao.deleteEmployer(deleteEmployerDtoRequest.getToken());
        return gson.toJson(OK_MESSAGE);
    }

    public String getEmployer(String requestJsonString) throws ServerException {
        GetEmployerDtoResponse getEmployerDTOResponse = createEmployerResponse(dao.getEmployer(getTokenFromJson(requestJsonString)));
        return gson.toJson(getEmployerDTOResponse);
    }

    private GetEmployerDtoResponse createEmployerResponse(Employer employer) throws ServerException {
        return new GetEmployerDtoResponse(employer.getName(), employer.getMiddleName(), employer.getLastName(),
                employer.getEmail(), employer.getLogin(), employer.getPassword(),
                employer.getCompanyName(), employer.getAddress(), employer.getVacancies());
    }

    public String updateEmployer(String requestJsonString) throws ServerException {
        UpdateEmployerDtoRequest updateEmployerDTORequest = getClassInstanceFromJson(requestJsonString, UpdateEmployerDtoRequest.class);
        Employer employer = createEmployer(updateEmployerDTORequest);
        dao.updateEmployer(updateEmployerDTORequest.getToken(), employer);
        return gson.toJson(OK_MESSAGE);
    }


    public String hireEmployee(String requestJsonString) throws ServerException {
        HireEmployeeDtoRequest hireEmployeeDtoRequest = getClassInstanceFromJson(requestJsonString, HireEmployeeDtoRequest.class);
        dao.hireEmployee(hireEmployeeDtoRequest.getEmployeeToken(), hireEmployeeDtoRequest.getEmployerToken(), hireEmployeeDtoRequest.getVacancyName());
        return gson.toJson(OK_MESSAGE);
    }

    public String getBestEmployees(String requestJsonString) throws ServerException {
        VacancyDtoRequest vacancyDtoRequest = getClassInstanceFromJson(requestJsonString, VacancyDtoRequest.class);
        Vacancy vacancy = createVacancy(vacancyDtoRequest);
        Set<Employee> employees = dao.getBestEmployees(vacancy);
        GetBestEmployeesDtoResponse getBestEmployeesDtoResponse = new GetBestEmployeesDtoResponse(employees);
        return gson.toJson(getBestEmployeesDtoResponse);
    }

    public String getGoodEmployees(String requestJsonString) throws ServerException {
        VacancyDtoRequest vacancyDtoRequest = getClassInstanceFromJson(requestJsonString, VacancyDtoRequest.class);
        Vacancy vacancy = createVacancy(vacancyDtoRequest);
        Set<Employee> employees = dao.getGoodEmployees(vacancy);
        GetGoodEmployeesDtoResponse getGoodEmployeesDTOResponse = new GetGoodEmployeesDtoResponse(employees);
        return gson.toJson(getGoodEmployeesDTOResponse);
    }

    public String getAnyLevelEmployees(String requestJsonString) throws ServerException {
        VacancyDtoRequest vacancyDtoRequest = getClassInstanceFromJson(requestJsonString, VacancyDtoRequest.class);
        Vacancy vacancy = createVacancy(vacancyDtoRequest);
        Set<Employee> employees = dao.getAnyLevelEmployees(vacancy);
        GetAnyLevelEmployeesDtoResponse getAnyLevelEmployeesDtoResponse = new GetAnyLevelEmployeesDtoResponse(employees);
        return gson.toJson(getAnyLevelEmployeesDtoResponse);
    }

    public String getAsLeastOneRequirementEmployees(String requestJsonString) throws ServerException {
        VacancyDtoRequest vacancyDtoRequest = getClassInstanceFromJson(requestJsonString, VacancyDtoRequest.class);
        Vacancy vacancy = createVacancy(vacancyDtoRequest);
        Set<Employee> employees = dao.getAsLeastOneRequirementEmployees(vacancy);
        GetOneRequirementEmployeesDtoResponse getAnyLevelEmployeesDTOResponse = new GetOneRequirementEmployeesDtoResponse(employees);
        return gson.toJson(getAnyLevelEmployeesDTOResponse);
    }

    // VACANCY METHODS
    public String addVacancy(String requestJsonString) throws ServerException {
        VacancyDtoRequest vacancyDtoRequest = getClassInstanceFromJson(requestJsonString, VacancyDtoRequest.class);
        Vacancy vacancy = createVacancy(vacancyDtoRequest);
        vacancy.setEmployerToken(vacancyDtoRequest.getToken());
        dao.addVacancy(vacancyDtoRequest.getToken(), vacancy);
        return gson.toJson(OK_MESSAGE);
    }

    public String getVacancies(String requestJsonString) throws ServerException {
        return gson.toJson(dao.getVacancies(getTokenFromJson(requestJsonString)));
    }

    public String removeVacancy(String requestJsonString) throws ServerException {
        RemoveVacancyDtoRequest removeVacancyDtoRequest = getClassInstanceFromJson(requestJsonString, RemoveVacancyDtoRequest.class);
        dao.removeVacancy(removeVacancyDtoRequest.getToken(), removeVacancyDtoRequest.getVacancyName());
        return gson.toJson(OK_MESSAGE);
    }

    public String addVacancyRequirement(String requestJsonString) throws ServerException {
        AddVacancyRequirementDtoRequest addVacancyRequirementDtoRequest;
        addVacancyRequirementDtoRequest = getClassInstanceFromJson(requestJsonString, AddVacancyRequirementDtoRequest.class);
        Requirement requirement = createRequirement(addVacancyRequirementDtoRequest);
        dao.addVacancyRequirement(addVacancyRequirementDtoRequest.getToken(), addVacancyRequirementDtoRequest.getVacancyName(), requirement);
        return gson.toJson(OK_MESSAGE);
    }

    public String removeVacancyRequirement(String requestJsonString) throws ServerException {
        RemoveVacancyRequirementDtoRequest removeVacancyRequirementDtoRequest = getClassInstanceFromJson(requestJsonString, RemoveVacancyRequirementDtoRequest.class);
        Requirement requirement = createRequirement(removeVacancyRequirementDtoRequest);
        dao.removeVacancyRequirement(removeVacancyRequirementDtoRequest.getToken(), removeVacancyRequirementDtoRequest.getVacancyName(), requirement);
        return gson.toJson(OK_MESSAGE);
    }

    public String setVacancyActivity(String requestJsonString) throws ServerException {
        ActivityVacancyDtoRequest activityVacancyDtoRequest = getClassInstanceFromJson(requestJsonString, ActivityVacancyDtoRequest.class);
        dao.setVacancyActivity(activityVacancyDtoRequest.getToken(), activityVacancyDtoRequest.getVacancyName(), activityVacancyDtoRequest.isActive());
        return gson.toJson(OK_MESSAGE);
    }
}
