package net.thumbtack.school.hiring.request.vacancy;

import net.thumbtack.school.hiring.exception.ServerErrorCode;
import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.settings.Settings;
import net.thumbtack.school.hiring.vacancy.Requirement;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;

public class VacancyDtoRequest {

    private String name;
    private int salary;
    private Set<Requirement> requirements;
    private boolean isActive;
    private String token;

    public VacancyDtoRequest(String name, int salary, Set<Requirement> requirements, String token) throws ServerException {
        setName(name);
        setSalary(salary);
        setRequirements(requirements);
        setToken(token);
        isActive = true;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public Set<Requirement> getRequirements() {
        return requirements;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getToken() {
        return token;
    }

    public void setName(String name) throws ServerException {
        if(name == null || name.equals("")){
            throw new ServerException(ServerErrorCode.VACANCY_WRONG_NAME);
        }
        this.name = name;
    }

    private void setSalary(int salary) {
        this.salary = salary;
    }

    private void setRequirements(Set<Requirement> requirements) {
        if(requirements == null){
            this.requirements = new HashSet<>();
        }else{
            this.requirements = requirements;
        }
    }

    private void setToken(String token) throws ServerException {
        if(!isValidToken(token)){
            throw new ServerException(ServerErrorCode.SERVER_WRONG_TOKEN);
        }
        this.token = token;
    }
    private static boolean isValidToken(String token) {
        Matcher matcher = Settings.VALID_TOKEN.matcher(token);
        return matcher.find();
    }
}
