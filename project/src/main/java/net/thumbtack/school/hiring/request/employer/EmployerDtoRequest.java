package net.thumbtack.school.hiring.request.employer;

import net.thumbtack.school.hiring.exception.ServerErrorCode;
import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.request.person.PersonDtoRequest;
import net.thumbtack.school.hiring.vacancy.Vacancy;

import java.util.HashSet;
import java.util.Set;

//REVU BaseEmployerDtoRequest or CommonEmployerDtoRequest
public class EmployerDtoRequest extends PersonDtoRequest {

    private String companyName;
    private String address;
    private Set<Vacancy> vacancies;

    public EmployerDtoRequest(String name, String middleName, String lastName, String email, String login, String password, String companyName, String address, Set<Vacancy> vacancies) throws ServerException {
        super(name, middleName, lastName, email, login, password);
        setCompanyName(companyName);
        setAddress(address);
        setVacancies(vacancies);
    }

    public EmployerDtoRequest(String name, String middleName, String lastName, String email, String login, String password, String companyName, String address) throws ServerException {
        this(name, middleName, lastName, email, login, password, companyName, address, null);
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getAddress() {
        return address;
    }

    public Set<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setCompanyName(String companyName) throws ServerException {
        if (companyName == null || companyName.trim().equals("")) {
            throw new ServerException(ServerErrorCode.EMPLOYER_WRONG_NULL_COMPANYNAME);
        }
        this.companyName = companyName;
    }

    public void setAddress(String address) throws ServerException {
        if (address == null || address.trim().equals("")) {
            throw new ServerException(ServerErrorCode.EMPLOYER_WRONG_NULL_ADDRESS);
        }
        this.address = address;
    }

    public void setVacancies(Set<Vacancy> vacancies) {
        if (vacancies == null) {
            this.vacancies = new HashSet<>();
        } else {
            this.vacancies = vacancies;
        }
    }
}
