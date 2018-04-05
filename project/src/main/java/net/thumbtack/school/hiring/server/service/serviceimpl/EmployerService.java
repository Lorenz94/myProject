package net.thumbtack.school.hiring.server.service.serviceimpl;

import net.thumbtack.school.hiring.exception.ServerException;

public interface EmployerService extends VacancyService {
    String registerEmployer(String requestJsonString) throws ServerException;

    String logOffEmployer(String requestJsonString) throws ServerException;

    String logOnEmployer(String requestJsonString) throws ServerException;

    String deleteEmployer(String requestJsonString) throws ServerException;

    String getEmployer(String requestJsonString) throws ServerException;

    String updateEmployer(String requestJsonString) throws ServerException;

    String hireEmployee(String requestJsonString) throws ServerException;

    String getBestEmployees(String requestJsonString) throws ServerException;

    String getGoodEmployees(String requestJsonString) throws ServerException;

    String getAnyLevelEmployees(String requestJsonString) throws ServerException;

    String getAsLeastOneRequirementEmployees(String requestJsonString) throws ServerException;


}
