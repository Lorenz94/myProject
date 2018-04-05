package net.thumbtack.school.hiring.request.employer;

import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.vacancy.Vacancy;

import java.util.Set;

public class RegisterEmployerDtoRequest extends EmployerDtoRequest {

    public RegisterEmployerDtoRequest(String name, String middleName, String lastName, String email, String login, String password, String companyName, String address, Set<Vacancy> vacancies) throws ServerException {
        super(name, middleName, lastName, email, login, password, companyName, address, vacancies);
    }
    public RegisterEmployerDtoRequest(String name, String middleName, String lastName, String email, String login, String password, String companyName, String address) throws ServerException {
        this(name, middleName, lastName, email, login, password, companyName, address, null);
    }

}
