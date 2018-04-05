package net.thumbtack.school.hiring.server.service;

import net.thumbtack.school.hiring.response.skill.GetSkillsDtoResponse;
import net.thumbtack.school.hiring.response.vacancy.GetAnyLevelVacanciesDtoResponse;
import net.thumbtack.school.hiring.response.vacancy.GetBestVacanciesDtoResponse;
import net.thumbtack.school.hiring.response.employee.GetEmployeeDtoResponse;
import net.thumbtack.school.hiring.response.vacancy.GetGoodVacanciesDtoResponse;
import net.thumbtack.school.hiring.server.dao.EmployeeDaoImpl;
import net.thumbtack.school.hiring.request.skill.SkillDtoRequest;
import net.thumbtack.school.hiring.request.employee.*;
import net.thumbtack.school.hiring.person.Employee;
import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.server.service.serviceimpl.EmployeeService;
import net.thumbtack.school.hiring.vacancy.Skill;
import net.thumbtack.school.hiring.vacancy.Vacancy;

import java.util.Set;

public class EmployeeServiceImpl extends ServiceUtil implements EmployeeService {

	private static EmployeeDaoImpl dao;
    public EmployeeServiceImpl() {
        dao = new EmployeeDaoImpl();
    }


    public String registerEmployee(String requestJsonString) throws ServerException {
        RegisterEmployeeDtoRequest registerEmployeeDTORequest;
        registerEmployeeDTORequest = getClassInstanceFromJson(requestJsonString, RegisterEmployeeDtoRequest.class);
        String token = createToken();
        Employee employee = createEmployee(registerEmployeeDTORequest);
        dao.registerEmployee(token, employee);
        return gson.toJson(token);
    }

    public String deleteEmployee(String requestJsonString) throws ServerException {
        DeleteEmployeeDtoRequest deleteEmployeeDtoRequest;
        deleteEmployeeDtoRequest = getClassInstanceFromJson(requestJsonString, DeleteEmployeeDtoRequest.class);
        dao.deleteEmployee(deleteEmployeeDtoRequest.getToken());
        return gson.toJson(OK_MESSAGE);
    }


    public String logOffEmployee(String requestJsonString) throws ServerException {
        dao.logOffEmployee(getTokenFromJson(requestJsonString));
        return gson.toJson(OK_MESSAGE);
    }


    public String logOnEmployee(String requestJsonString) throws ServerException {
        dao.logOnEmployee(getTokenFromJson(requestJsonString));
        return gson.toJson(OK_MESSAGE);
    }

    public String getEmployee(String requestJsonString) throws ServerException {
        Employee employee = dao.getEmployee(getTokenFromJson(requestJsonString));
        GetEmployeeDtoResponse getEmployeeDTOResponse = createEmployeeResponse(employee);
        return gson.toJson(getEmployeeDTOResponse);
    }

    private GetEmployeeDtoResponse createEmployeeResponse(Employee employee) throws ServerException {
        return new GetEmployeeDtoResponse(employee.getName(), employee.getMiddleName(), employee.getLastName(), employee.getEmail(), employee.getLogin(), employee.getPassword(), employee.getSkills(),employee.isActive());
    }

    public String setActivity(String requestJsonString) throws ServerException {
        ChangeActivityEmployeeDtoRequest data = gson.fromJson(requestJsonString, ChangeActivityEmployeeDtoRequest.class);
        dao.setActivityEmployee(data.getToken(), data.isActive());
        return gson.toJson(OK_MESSAGE);
    }


    public String updateEmployee(String requestJsonString) throws ServerException {
        UpdateEmployeeDtoRequest updateEmployeeDTORequest = getClassInstanceFromJson(requestJsonString, UpdateEmployeeDtoRequest.class);
        Employee employee = createEmployee(updateEmployeeDTORequest);
        dao.updateEmployee(updateEmployeeDTORequest.getToken(), employee);
        return gson.toJson(OK_MESSAGE);
    }


    public String getBestVacancies(String requestJsonString) throws ServerException {
        Set<Vacancy> bestVacancies = dao.getBestVacancies(getTokenFromJson(requestJsonString));
        GetBestVacanciesDtoResponse getBestVacanciesDtoResponse = new GetBestVacanciesDtoResponse(bestVacancies);
        return gson.toJson(getBestVacanciesDtoResponse);
    }

    public String getGoodVacancies(String requestJsonString) throws ServerException {
        Set<Vacancy> goodVacancies = dao.getGoodVacancies(getTokenFromJson(requestJsonString));
        GetGoodVacanciesDtoResponse getGoodVacanciesDtoResponse = new GetGoodVacanciesDtoResponse(goodVacancies);
        return gson.toJson(getGoodVacanciesDtoResponse);
    }

    public String getAnyLevelVacancies(String requestJsonString) throws ServerException {
        Set<Vacancy> anyLevelVacancies = dao.getAnyLevelVacancies(getTokenFromJson(requestJsonString));
        GetAnyLevelVacanciesDtoResponse getAnyLevelVacanciesDtoResponse = new GetAnyLevelVacanciesDtoResponse(anyLevelVacancies);
        return gson.toJson(getAnyLevelVacanciesDtoResponse);
    }

    public String getAsLeastOneRequirementVacancies(String requestJsonString) throws ServerException {
        Set<Vacancy> asLeastOneRequirement = dao.getAsLeastOneRequirementVacancies(getTokenFromJson(requestJsonString));
        GetAnyLevelVacanciesDtoResponse getAnyLevelVacanciesDtoResponse = new GetAnyLevelVacanciesDtoResponse(asLeastOneRequirement);
        return gson.toJson(getAnyLevelVacanciesDtoResponse);
    }


    //SKILL METHODS
    public String addSkill(String requestJsonString) throws ServerException {
        SkillDtoRequest skillDtoRequest = getClassInstanceFromJson(requestJsonString, SkillDtoRequest.class);
        Skill skill = createSkill(skillDtoRequest);
        dao.addSkill(skillDtoRequest.getToken(), skill);
        return gson.toJson(OK_MESSAGE);
    }

    public String removeSkill(String requestJsonString) throws ServerException {
        SkillDtoRequest skillDtoRequest = getClassInstanceFromJson(requestJsonString, SkillDtoRequest.class);
        Skill skill = createSkill(skillDtoRequest);
        dao.removeSkill(skillDtoRequest.getToken(), skill);
        return gson.toJson(OK_MESSAGE);
    }

    public String getSkills(String requestJsonString) throws ServerException {
        GetSkillsDtoResponse getSkillsDtoResponse = new GetSkillsDtoResponse(dao.getSkills(getTokenFromJson(requestJsonString)));
        return gson.toJson(getSkillsDtoResponse);
    }

    public String changeSkillScore(String requestJsonString) throws ServerException {
        SkillDtoRequest skillDtoRequest = getClassInstanceFromJson(requestJsonString, SkillDtoRequest.class);
        Skill skill = createSkill(skillDtoRequest);
        dao.changeSkillScore(skillDtoRequest.getToken(), skill);
        return gson.toJson(OK_MESSAGE);
    }
}
