package net.thumbtack.school.hiring.response.employer;

import net.thumbtack.school.hiring.exception.ServerErrorCode;
import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.settings.Settings;
import net.thumbtack.school.hiring.vacancy.Vacancy;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;

public class EmployerDtoResponse {
    private String name;
    private String middleName;
    private String lastName;
    private String email;
    private String login;
    private String password;
    private String companyName;
    private String address;
    private Set<Vacancy> vacancies;

    public EmployerDtoResponse(String name, String middleName, String lastName, String email, String login, String password, String companyName, String address, Set<Vacancy> vacancies) throws ServerException {
        setName(name);
        setMiddleName(middleName);
        setLastName(lastName);
        setEmail(email);
        setLogin(login);
        setPassword(password);
        setCompanyName(companyName);
        setAddress(address);
        setVacancies(vacancies);
    }

    public String getName() {
        return name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
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

    public void setName(String name) throws ServerException {
        if (name == null || name.trim().equals("")) {
            throw new ServerException(ServerErrorCode.PERSON_EMPTY_NAME);
        }
        this.name = name;
    }

    public void setMiddleName(String middleName) {
        if (middleName == null) middleName = "";
        this.middleName = middleName;
    }

    public void setLastName(String lastName) throws ServerException {
        if (lastName == null || lastName.trim().equals("")) {
            throw new ServerException(ServerErrorCode.PERSON_EMPTY_LASTNAME);
        }
        this.lastName = lastName;
    }

    public void setEmail(String email) throws ServerException {
        if (email == null || email.trim().equals("")) {
            throw new ServerException(ServerErrorCode.PERSON_NULL_EMAIL);
        }
        if(!isValidEmailAddress(email)){
            throw new ServerException(ServerErrorCode.PERSON_WRONG_EMAIL, email);
        }
        this.email = email;
    }

    private static boolean isValidEmailAddress(String email) {
        Matcher matcher = Settings.VALID_EMAIL.matcher(email);
        return matcher.find();
    }

    private void setLogin(String login) throws ServerException {
        if (login == null || login.trim().equals("")) {
            throw new ServerException(ServerErrorCode.PERSON_NULL_LOGIN);
        }
        this.login = login;
    }

    public void setPassword(String password) throws ServerException {
        if (password == null || password.trim().equals("")) {
            throw new ServerException(ServerErrorCode.PERSON_NULL_PASSWORD);
        }
        if(!isValidPassword(password)){
            throw new ServerException(ServerErrorCode.PERSON_WRONG_PASSWORD);
        }
        this.password = password;
    }

    private static boolean isValidPassword(String password){
        return password.length() >= Settings.PASSWORD_LENGTH;
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
