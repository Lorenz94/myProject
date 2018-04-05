// REVU vice versa
// this package must contain implementation
// net.thumbtack.school.hiring.server.dao must contain interfaces

package net.thumbtack.school.hiring.server.dao.daoimpl;


import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.person.Employee;
import net.thumbtack.school.hiring.vacancy.Skill;
import net.thumbtack.school.hiring.vacancy.Vacancy;


import java.util.Set;


public interface EmployeeDao {

    void registerEmployee(String token, Employee employee) throws ServerException;

    void deleteEmployee(String token) throws ServerException;

    void logOffEmployee(String token) throws ServerException;

    void logOnEmployee(String token) throws ServerException;

    Employee getEmployee(String token) throws ServerException;

    void setActivityEmployee(String token, boolean active) throws ServerException;

    void updateEmployee(String token, Employee employee) throws ServerException;

    Set<Vacancy> getBestVacancies(String token) throws ServerException;

    Set<Vacancy> getGoodVacancies(String token) throws ServerException;

    Set<Vacancy> getAnyLevelVacancies(String token) throws ServerException;

    Set<Vacancy> getAsLeastOneRequirementVacancies(String token) throws ServerException;

    void addSkill(String token, Skill skill) throws ServerException;

    void removeSkill(String token, Skill skill) throws ServerException;

    Set<Skill> getSkills(String token) throws ServerException;

    void changeSkillScore(String token, Skill skill) throws ServerException;
}
