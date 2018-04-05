package net.thumbtack.school.hiring.server;

import java.io.*;

import net.thumbtack.school.hiring.exception.ServerErrorCode;
import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.server.service.EmployeeServiceImpl;
import net.thumbtack.school.hiring.server.service.EmployerServiceImpl;
import net.thumbtack.school.hiring.server.service.ServerServiceImpl;


public class Server {

    private boolean isServerOn = false;

    private EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
    private EmployerServiceImpl employerService = new EmployerServiceImpl();
    private ServerServiceImpl serverService = new ServerServiceImpl();

    public Server() {
    }

    public void startServer(){
        serverService.startServer();
        isServerOn = true;
    }

    // REVU This class methods must not throw exceptions.
    // they must catch them and return json with "error" field

    public void startServer(String loadedDataFileName) throws IOException, ServerException {
        serverService.startServer(loadedDataFileName);
        isServerOn = true;
    }

    public void stopServer(String saveDataFileName) throws IOException, ServerException {
        serverService.stopServer(saveDataFileName);
        isServerOn = false;
    }

    public void stopServer(){
        isServerOn = false;
    }


    private boolean isServerOn(String message) throws ServerException {
        if (!isServerOn) {
            throw new ServerException(ServerErrorCode.SERVER_IS_OFF, message);
        }
        return true;
    }


    //****** EMPLOYEE METHODS *********
    public String registerEmployee(String requestJsonString) throws ServerException {
        isServerOn("register employee");
        return employeeService.registerEmployee(requestJsonString);
    }

    public String deleteEmployee(String requestJsonString) throws ServerException {
        isServerOn("delete employee");
        return employeeService.deleteEmployee(requestJsonString);
    }

    public String logOffEmployee(String requestJsonString) throws ServerException {
        isServerOn("log off employee");
        return employeeService.logOffEmployee(requestJsonString);
    }

    public String logOnEmployee(String requestJsonString) throws ServerException {
        isServerOn("log on employee");
        return employeeService.logOnEmployee(requestJsonString);
    }

    public String getEmployee(String requestJsonString) throws ServerException {
        isServerOn("get employee");
        return employeeService.getEmployee(requestJsonString);
    }

    public String setActivity(String requestJsonString) throws ServerException {
        isServerOn("change employee activity");
        return employeeService.setActivity(requestJsonString);
    }

    public String updateEmployee(String requestJsonString) throws ServerException {
        isServerOn("update employee.");
        return employeeService.updateEmployee(requestJsonString);
    }

    //******** SKILL METHODS **********
    public String addSkill(String requestJsonString) throws ServerException {
        isServerOn("add skill");
        return employeeService.addSkill(requestJsonString);
    }

    public String removeSkill(String requestJsonString) throws ServerException {
        isServerOn("remove skill");
        return employeeService.removeSkill(requestJsonString);
    }

    public String getSkills(String requestJsonString) throws ServerException {
        isServerOn("get skills");
        return employeeService.getSkills(requestJsonString);
    }

    public String changeSkillScore(String requestJsonString) throws ServerException {
        isServerOn("change skill score");
        return employeeService.changeSkillScore(requestJsonString);
    }


    public String getBestVacancies(String requestJsonString) throws ServerException {
        isServerOn("get vacancies");
        return employeeService.getBestVacancies(requestJsonString);
    }

    public String getGoodVacancies(String requestJsonString) throws ServerException {
        isServerOn("get vacancies");
        return employeeService.getGoodVacancies(requestJsonString);
    }

    public String getAnyLevelVacancies(String requestJsonString) throws ServerException {
        isServerOn("get vacancies");
        return employeeService.getAnyLevelVacancies(requestJsonString);
    }

    public String getAsLeastOneRequirementVacancies(String requestJsonString) throws ServerException {
        isServerOn("get vacancies");
        return employeeService.getAsLeastOneRequirementVacancies(requestJsonString);
    }


    //******EMPLOYER METHODS*****
    public String registerEmployer(String requestJsonString) throws ServerException {
        isServerOn("register employer");
        return employerService.registerEmployer(requestJsonString);
    }

    public String logOffEmployer(String requestJsonString) throws ServerException {
        isServerOn("log off");
        return employerService.logOffEmployer(requestJsonString);
    }

    public String logOnEmployer(String requestJsonString) throws ServerException {
        isServerOn("log on");
        return employerService.logOnEmployer(requestJsonString);
    }

    public String deleteEmployer(String requestJsonString) throws ServerException {
        isServerOn("delete");
        return employerService.deleteEmployer(requestJsonString);
    }

    public String getEmployer(String requestJsonString) throws ServerException {
        isServerOn("get Employer");
        return employerService.getEmployer(requestJsonString);
    }

    public String updateEmployer(String requestJsonString) throws ServerException {
        isServerOn("update employer");
        return employerService.updateEmployer(requestJsonString);
    }

    public String hireEmployee(String requestJsonString) throws ServerException {
        isServerOn("hire employee");
        return employerService.hireEmployee(requestJsonString);
    }


    public String getBestEmployees(String requestJsonString) throws ServerException {
        isServerOn("get employees");
        return employerService.getBestEmployees(requestJsonString);
    }

    public String getGoodEmployees(String requestJsonString) throws ServerException {
        isServerOn("get employees");
        return employerService.getGoodEmployees(requestJsonString);
    }

    public String getAnyLevelEmployees(String requestJsonString) throws ServerException {
        isServerOn("get employees");
        return employerService.getAnyLevelEmployees(requestJsonString);
    }

    public String getAsLeastOneRequirement(String requestJsonString) throws ServerException {
        isServerOn("get employees");
        return employerService.getAsLeastOneRequirementEmployees(requestJsonString);
    }


    // ************* VACANCY METHODS *********
    public String addVacancy(String requestJsonString) throws ServerException {
        isServerOn("add vacancy");
        return employerService.addVacancy(requestJsonString);
    }

    public String getVacancies(String requestJsonString) throws ServerException{
        isServerOn("get vacancies");
        return employerService.getVacancies(requestJsonString);
    }

    public String removeVacancy(String requestJsonString) throws ServerException {
        isServerOn("remove vacancy");
        return employerService.removeVacancy(requestJsonString);
    }

    public String addVacancyRequirement(String requestJsonString) throws ServerException {
        isServerOn("add requirement");
        return employerService.addVacancyRequirement(requestJsonString);
    }

    public String removeVacancyRequirement(String requestJsonString) throws ServerException {
        isServerOn("remove requirement");
        return employerService.removeVacancyRequirement(requestJsonString);
    }

    public String setVacancyActivity(String requestJsonString) throws ServerException {
        isServerOn("change vacancy to inactive");
        return employerService.setVacancyActivity(requestJsonString);
    }


}
