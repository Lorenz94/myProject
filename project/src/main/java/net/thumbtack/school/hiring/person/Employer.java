package net.thumbtack.school.hiring.person;

import net.thumbtack.school.hiring.exception.ServerErrorCode;
import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.vacancy.Vacancy;

import java.util.*;

public class Employer extends Person {

    private String companyName;
    private String address;
    private Set<Vacancy> vacancies;

    public Employer(String name, String middleName, String lastName, String email, String login, String password, String companyName, String address) throws ServerException {
        this(name, middleName, lastName, email, login, password, companyName, address, null);
    }

    public Employer(String name, String lastName, String email, String login, String password, String companyName, String address) throws ServerException {
        this(name, "", lastName, email, login, password, companyName, address);
    }

    public Employer(String name,String middleName, String lastName, String email, String login, String password, String companyName, String address, Set<Vacancy> vacancies) throws ServerException {
        super(name, middleName, lastName, email, login, password);
        setCompanyName(companyName);
        setAddress(address);
        setVacancies(vacancies);
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) throws ServerException {
        if (companyName == null || companyName.trim().equals("")) {
            throw new ServerException(ServerErrorCode.EMPLOYER_WRONG_NULL_COMPANYNAME);
        }
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) throws ServerException {
        if (address == null || address.trim().equals("")) {
            throw new ServerException(ServerErrorCode.EMPLOYER_WRONG_NULL_ADDRESS);
        }
        this.address = address;
    }

    public Set<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setVacancies(Set<Vacancy> vacancies) {
        if (vacancies == null) {
            initVacancies();
        } else {
            this.vacancies = vacancies;
        }
    }

    private void initVacancies() {
        this.vacancies = new HashSet<>();
    }

    public void addVacancy(Vacancy vacancy) {
        vacancies.add(vacancy);
    }

    public void removeVacancy(Vacancy vacancy) throws ServerException {
        if (!vacancies.remove(vacancy)) {
            throw new ServerException(ServerErrorCode.EMPLOYER_VACANCY_NOT_FOUND, vacancy.getName());
        }
    }

    public void removeVacancy(String name) throws ServerException {
        vacancies.remove(containsVacancy(name));
    }

    public Vacancy getVacancy(String name) throws ServerException {
        return containsVacancy(name);
    }

    //Поправить
    private Vacancy containsVacancy(String name) throws ServerException {
        for (Vacancy vacancy : vacancies) {
            if (vacancy.getName().equals(name)) return vacancy;
        }
        throw new ServerException(ServerErrorCode.EMPLOYER_VACANCY_NOT_FOUND, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employer employer = (Employer) o;
        return Objects.equals(companyName, employer.companyName) &&
                Objects.equals(address, employer.address) &&
                Objects.equals(vacancies, employer.vacancies);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), companyName, address, vacancies);
    }
}
