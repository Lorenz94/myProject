package net.thumbtack.school.hiring.response.database;

import net.thumbtack.school.hiring.person.Employee;
import net.thumbtack.school.hiring.person.Employer;
import net.thumbtack.school.hiring.vacancy.Vacancy;
import java.util.Map;
import java.util.Set;

public class SaveDataBaseDtoResponse {
    private Map<String, Employee> mapEmployees;
    private Map<String, Employer> mapEmployers;
    private Map<String, Set<Vacancy>> mapRankedVacancies;
    private Map<String, Set<String>> mapRankedEmployees;
    private Set<String> logins;


    public SaveDataBaseDtoResponse(Map<String, Employee> mapEmployees, Map<String, Employer> mapEmployers, Map<String, Set<Vacancy>> mapRankedVacancies, Map<String, Set<String>> mapRankedEmployees, Set<String> logins) {
        setMapEmployees(mapEmployees);
        setMapEmployers(mapEmployers);
        setMapRankedVacancies(mapRankedVacancies);
        setMapRankedEmployees(mapRankedEmployees);
        setLogins(logins);
    }

    //Доделать проверку на стадии тестирования
    private void setMapEmployees(Map<String, Employee> mapEmployees) {
        this.mapEmployees = mapEmployees;
    }

    private void setMapEmployers(Map<String, Employer> mapEmployers) {
        this.mapEmployers = mapEmployers;
    }

    private void setMapRankedVacancies(Map<String, Set<Vacancy>> mapRankedVacancies) {
        this.mapRankedVacancies = mapRankedVacancies;
    }

    private void setMapRankedEmployees(Map<String, Set<String>> mapRankedEmployees) {
        this.mapRankedEmployees = mapRankedEmployees;
    }

    public void setLogins(Set<String> logins) {
        this.logins = logins;
    }
}
