package net.thumbtack.school.hiring.vacancy;

import net.thumbtack.school.hiring.exception.ServerErrorCode;
import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.settings.Settings;

import java.util.*;
import java.util.regex.Matcher;

public class Vacancy {

    private String name;
    private int salary;
    private Set<Requirement> requirements;
    private boolean isActive;
    private String employerToken;

    public Vacancy(String name, int salary, Set<Requirement> requirements) throws ServerException {
        setName(name);
        setSalary(salary);
        setRequirements(requirements);
        this.isActive = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws ServerException {
       if(name == null || name.equals("")){
           throw new ServerException(ServerErrorCode.VACANCY_WRONG_NAME);
       }
       this.name = name;
    }

    public void setEmployerToken(String employerToken) throws ServerException {
        if(!isValidToken(employerToken)){
            throw new ServerException(ServerErrorCode.SERVER_WRONG_TOKEN);
        }
        this.employerToken = employerToken;
    }

    public String getEmployerToken() {
        return employerToken;
    }

    private static boolean isValidToken(String employerToken) {
        Matcher matcher = Settings.VALID_TOKEN.matcher(employerToken);
        return matcher.find();
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Set<Requirement> getRequirements() {
        return requirements;
    }

    public Set<Requirement> getMandatoryRequirements() {
        Set<Requirement> result = new HashSet<>();
        for (Requirement requirement : getRequirements()) {
            if(requirement.getMandatory()){
                result.add(requirement);
            }
        }
        return result;
    }

    private void setRequirements(Set<Requirement> requirements) {
        if(requirements == null){
            this.requirements = new HashSet<>();
        }else{
            this.requirements = requirements;
        }
    }

    public void addRequirement(Requirement requirement){
       requirements.add(requirement);
    }

    public void deleteRequirement(Requirement requirement) throws ServerException {
        if(!requirements.remove(requirement)){
            throw new ServerException(ServerErrorCode.REQUIREMENT_NOT_FOUND, requirement.getName());
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return salary == vacancy.salary &&
                isActive == vacancy.isActive &&
                Objects.equals(name, vacancy.name) &&
                Objects.equals(requirements, vacancy.requirements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, salary, requirements, isActive);
    }
}
