package net.thumbtack.school.hiring.request.employee;

import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.vacancy.Skill;

import java.util.Set;

public class RegisterEmployeeDtoRequest extends BaseEmployeeDtoRequest {

    public RegisterEmployeeDtoRequest(String name, String middleName, String lastName, String email, String login, String password, Set<Skill> skills) throws ServerException {
        super(name, middleName, lastName, email, login, password, skills);
    }
}
