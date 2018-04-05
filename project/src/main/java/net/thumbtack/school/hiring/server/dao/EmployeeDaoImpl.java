package net.thumbtack.school.hiring.server.dao;

import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.person.Employee;
import net.thumbtack.school.hiring.server.dao.daoimpl.EmployeeDao;
import net.thumbtack.school.hiring.vacancy.Skill;
import net.thumbtack.school.hiring.vacancy.Vacancy;
import java.util.Set;

public class EmployeeDaoImpl extends BaseDao implements EmployeeDao {

    public void registerEmployee(String token, Employee employee) throws ServerException {
        dataBase.insertEmployee(token, employee);
    }

    public void deleteEmployee(String token) throws ServerException {
        dataBase.deleteEmployee(token);
    }

    public void logOffEmployee(String token) throws ServerException {
        dataBase.logOffEmployee(token);
    }

    public void logOnEmployee(String token) throws ServerException {
        dataBase.logOnEmployee(token);
    }

    public Employee getEmployee(String token) throws ServerException {
        return dataBase.getEmployee(token);
    }

    public void setActivityEmployee(String token, boolean active) throws ServerException {
        dataBase.setActivityEmployee(token, active);
    }

    public void updateEmployee(String token, Employee employee) throws ServerException {
        dataBase.updateEmployee(token, employee);
    }

    public Set<Vacancy> getBestVacancies(String token) throws ServerException {
        return dataBase.getBestVacancies(token);
    }

    public Set<Vacancy> getGoodVacancies(String token) throws ServerException {
        return dataBase.getGoodVacancies(token);
    }

    public Set<Vacancy> getAnyLevelVacancies(String token) throws ServerException {
        return dataBase.getAnyLevelVacancies(token);
    }

    public Set<Vacancy> getAsLeastOneRequirementVacancies(String token) throws ServerException {
        return dataBase.getAsLeastOneRequirementVacancies(token);
    }

    public void addSkill(String token, Skill skill) throws ServerException {
        dataBase.insertSkill(token, skill);
    }

    public void removeSkill(String token, Skill skill) throws ServerException {
        dataBase.deleteSkill(token, skill);
    }

    public Set<Skill> getSkills(String token) throws ServerException {
        return dataBase.getSkills(token);
    }

    public void changeSkillScore(String token, Skill skill) throws ServerException {
        dataBase.updateSkill(token, skill);
    }
}
