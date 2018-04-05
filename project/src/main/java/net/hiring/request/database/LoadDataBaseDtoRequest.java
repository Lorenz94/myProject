package net.thumbtack.school.hiring.request.database;

import net.thumbtack.school.hiring.person.Employee;
import net.thumbtack.school.hiring.person.Employer;
import net.thumbtack.school.hiring.vacancy.Vacancy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LoadDataBaseDtoRequest {
    private Map<String, Employee> mapEmployees;
    private Map<String, Employer> mapEmployers;
    private Map<String, Set<Vacancy>> mapRankedVacancies;
    private Map<String, Set<String>> mapRankedEmployees;
    private Set<String> logins;

    public LoadDataBaseDtoRequest(Map<String, Employee> mapEmployees, Map<String, Employer> mapEmployers, Map<String, Set<Vacancy>> mapRankedVacancies, Map<String, Set<String>> mapRankedEmployees, Set<String> logins) {
        setMapEmployees(mapEmployees);
        setMapEmployers(mapEmployers);
        setMapRankedEmployees(mapRankedEmployees);
        setMapRankedVacancies(mapRankedVacancies);
        setLogins(logins);
    }

    public Set<String> getLogins() {
        return logins;
    }

    public Map<String, Employee> getMapEmployees() {
        return mapEmployees;
    }

    public Map<String, Employer> getMapEmployers() {
        return mapEmployers;
    }

    public Map<String, Set<Vacancy>> getMapRankedVacancies() {
        return mapRankedVacancies;
    }

    public Map<String, Set<String>> getMapRankedEmployees() {
        return mapRankedEmployees;
    }

    public void setMapEmployees(Map<String, Employee> mapEmployees) {
        if(mapEmployees == null){
            this.mapEmployees = new HashMap<>();
        }
        this.mapEmployees = mapEmployees;
    }

    public void setMapEmployers(Map<String, Employer> mapEmployers) {
        if(mapEmployers == null){
            this.mapEmployers = new HashMap<>();
        }
        this.mapEmployers = mapEmployers;
    }

    public void setMapRankedVacancies(Map<String, Set<Vacancy>> mapRankedVacancies) {
        if(mapRankedVacancies == null){
            this.mapRankedVacancies = new HashMap<>();
        }
        this.mapRankedVacancies = mapRankedVacancies;
    }

    public void setMapRankedEmployees(Map<String, Set<String>> mapRankedEmployees) {
        if(mapRankedEmployees == null){
            this.mapRankedEmployees = new HashMap<>();
        }
        this.mapRankedEmployees = mapRankedEmployees;
    }

    public void setLogins(Set<String> logins) {
        if(logins == null){
            this.logins = new HashSet<>();
        }
        this.logins = logins;
    }
}
