package net.thumbtack.school.hiring.request.employee;


import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.request.person.PersonDtoRequest;
import net.thumbtack.school.hiring.vacancy.Skill;

import java.util.HashSet;
import java.util.Set;

public class BaseEmployeeDtoRequest extends PersonDtoRequest {

    private Set<Skill> skills;

    public BaseEmployeeDtoRequest(String name, String middleName, String lastName, String email, String login, String password, Set<Skill> skills) throws ServerException {
        super(name,middleName,lastName,email,login,password);
        setSkills(skills);
    }


    public BaseEmployeeDtoRequest(String name, String middleName, String lastName, String email, String login, String password) throws ServerException {
        this(name, middleName, lastName, email, login, password, null);
    }


    public Set<Skill> getSkills() {
        return skills;
    }

    private void setSkills(Set<Skill> skills) {
        if (skills == null) {
            this.skills = new HashSet<>();
        }
        this.skills = skills;
    }
}
