// REVU rename to net.thumbtack.school.hiring.model
// this package will contain all "model" classes (may be, not only persons)
package net.thumbtack.school.hiring.person;

import net.thumbtack.school.hiring.exception.ServerErrorCode;
import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.vacancy.Skill;

import java.util.*;

public class Employee extends Person {

    private boolean isActive;
    private Set<Skill> skills;


    public Employee(String name, String middleName, String lastName, String email, String login, String password) throws ServerException {
        this(name, middleName, lastName, email, login, password, new TreeSet<>(Comparator.comparing(Skill::getName)));
    }

    public Employee(String name, String lastName, String email, String login, String password) throws ServerException {
        this(name, "", lastName, email, login, password);
    }

    public Employee(String name, String middleName, String lastName, String email, String login, String password, Set<Skill> skills) throws ServerException {
        super(name, middleName, lastName, email, login, password);
        setSkills(skills);
        this.isActive = true;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void addSkill(Skill skill) {
        if(skills == null){
            this.skills = new TreeSet<>(Comparator.comparing(Skill::getName));
        }
        skills.add(skill);
    }

    public void removeSkill(Skill skill) throws ServerException {
        if (!skills.remove(skill)) {
            throw new ServerException(ServerErrorCode.EMPLOYEE_SKILL_NOT_FOUND, skill.getName());
        }
    }

    public void removeSkill(String name) throws ServerException {
        skills.remove(containsSkill(name));
    }

    public Skill getSkill(String name) throws ServerException {
        return containsSkill(name);
    }

    private Skill containsSkill(String name) throws ServerException {
        for (Skill skill : skills) {
            if (skill.getName().equals(name)) return skill;
        }
        throw new ServerException(ServerErrorCode.EMPLOYEE_SKILL_NOT_FOUND);
    }

    public void setSkills(Set<Skill> skills) {
        if (skills == null) {
            this.skills = new TreeSet<>(Comparator.comparing(Skill::getName));
        }
        this.skills = skills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return isActive == employee.isActive &&
                Objects.equals(skills, employee.skills);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), isActive, skills);
    }
}
