package net.thumbtack.school.hiring.response.employee;

import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.vacancy.Skill;

import java.util.Set;

public class GetEmployeeDtoResponse extends EmployeeDtoResponse {
    public GetEmployeeDtoResponse(String name, String middleName, String lastName, String email, String login, String password, Set<Skill> skills, boolean active) throws ServerException {
        super(name, middleName, lastName, email, login, password, skills, active);
    }

}
