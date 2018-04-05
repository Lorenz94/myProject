// REVU vice versa
// this package must contain implementation
// net.thumbtack.school.hiring.server.service interfaces
package net.thumbtack.school.hiring.server.service.serviceimpl;

import net.thumbtack.school.hiring.exception.ServerException;

public interface EmployeeService extends SkillService {

    String registerEmployee(String requestJsonString) throws ServerException;
    String deleteEmployee(String requestJsonString) throws ServerException;
    String logOffEmployee(String requestJsonString) throws ServerException;
    String logOnEmployee(String requestJsonString) throws ServerException;
    String getEmployee(String requestJsonString) throws ServerException;
    String setActivity(String requestJsonString) throws ServerException;
    String updateEmployee(String requestJsonString) throws ServerException;

    String getBestVacancies(String requestJsonString) throws ServerException;
    String getGoodVacancies(String requestJsonString) throws ServerException;
    String getAnyLevelVacancies(String requestJsonString) throws ServerException;
    String getAsLeastOneRequirementVacancies(String requestJsonString) throws ServerException;


}
