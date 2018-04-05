package net.thumbtack.school.hiring.server.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.request.employee.BaseEmployeeDtoRequest;
import net.thumbtack.school.hiring.request.employer.EmployerDtoRequest;
import net.thumbtack.school.hiring.request.skill.SkillDtoRequest;
import net.thumbtack.school.hiring.request.vacancy.RequirementDtoRequest;
import net.thumbtack.school.hiring.request.vacancy.VacancyDtoRequest;
import net.thumbtack.school.hiring.person.Employee;
import net.thumbtack.school.hiring.person.Employer;
import net.thumbtack.school.hiring.vacancy.Requirement;
import net.thumbtack.school.hiring.vacancy.Skill;
import net.thumbtack.school.hiring.vacancy.Vacancy;

import java.util.UUID;


public abstract class ServiceUtil {

    protected static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    protected static final String ERROR_MESSAGE = "error";
    protected static final String OK_MESSAGE = "Ok";
    protected static final String DEVELOPMENT_MESSAGE = "Method in development";

    protected String createToken() {
        return String.valueOf(UUID.randomUUID());
    }

    protected String getTokenFromJson(String requestJsonString){
        String token;
        try {
            token = gson.fromJson(requestJsonString,String.class);
        } catch (JsonSyntaxException e) {
            return gson.toJson(ERROR_MESSAGE);
        }
        return token;
    }

    protected  <T> T getClassInstanceFromJson(String requestJsonString, Class<T> clazz) throws JsonSyntaxException {
        return gson.fromJson(requestJsonString, clazz);
    }

    protected Employer createEmployer(EmployerDtoRequest data) throws ServerException {
        return new Employer(data.getName(), data.getMiddleName(), data.getLastName(), data.getEmail(),
                data.getLogin(), data.getPassword(), data.getCompanyName(), data.getAddress());
    }

    protected Vacancy createVacancy(VacancyDtoRequest data) throws ServerException {
        return new Vacancy(data.getName(), data.getSalary(), data.getRequirements());
    }

    protected Requirement createRequirement(RequirementDtoRequest data) throws ServerException {
        return new Requirement(data.getName(),data.getScore(),data.isSurely());
    }

    protected Employee createEmployee(BaseEmployeeDtoRequest data) throws ServerException {
        return new Employee(data.getName(), data.getMiddleName(), data.getLastName(),
                data.getEmail(), data.getLogin(), data.getPassword(),data.getSkills());
    }

    protected Skill createSkill(SkillDtoRequest data) throws ServerException {
        return new Skill(data.getName(), data.getScore());
    }

}
