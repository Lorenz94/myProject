package net.thumbtack.school.hiring.database;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.exception.ServerErrorCode;
import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.request.database.LoadDataBaseDtoRequest;
import net.thumbtack.school.hiring.response.database.SaveDataBaseDtoResponse;
import net.thumbtack.school.hiring.person.Employee;
import net.thumbtack.school.hiring.person.Employer;
import net.thumbtack.school.hiring.vacancy.Skill;
import net.thumbtack.school.hiring.vacancy.Requirement;
import net.thumbtack.school.hiring.vacancy.Vacancy;

import java.io.*;
import java.util.*;

public class DataBase {

    private Map<String, Employee> mapEmployees;
    private Map<String, Employer> mapEmployers;
    private Map<String, Set<Vacancy>> mapRankedVacancies;
    private Map<String, Set<String>> mapRankedEmployees;
    private Set<String> logins;
    private Set<String> logOnEmployees;
    private Set<String> logOnEmployers;
    private static DataBase instance;


    public static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

    private DataBase() {
        initMaps();
    }

    private void initMaps() {
        mapEmployees = new HashMap<>();
        mapEmployers = new HashMap<>();
        mapRankedVacancies = new HashMap<>();
        mapRankedEmployees = new HashMap<>();
        logins = new HashSet<>();
        logOnEmployees = new HashSet<>();
        logOnEmployers = new HashSet<>();
    }

    public void loadDataBase(String savedDataFileName) throws IOException, ServerException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\resources\\" + savedDataFileName))) {
            Gson gson = new Gson();
            LoadDataBaseDtoRequest data = gson.fromJson(reader, LoadDataBaseDtoRequest.class);
            setMapEmployees(data.getMapEmployees());
            setMapEmployers(data.getMapEmployers());
            setMapRankedEmployees(data.getMapRankedEmployees());
            setMapRankedVacancies(data.getMapRankedVacancies());
            setLogins(data.getLogins());
        } catch (FileNotFoundException e) {
            throw new ServerException(ServerErrorCode.SERVER_FILE_NOT_FOUND, savedDataFileName, e);
        }
    }

    private void setMapEmployees(Map<String, Employee> mapEmployees) {
        if (mapEmployees == null) {
            this.mapEmployees = new HashMap<>();
        }
        this.mapEmployees = mapEmployees;
    }

    private void setMapEmployers(Map<String, Employer> mapEmployers) {
        if (mapEmployers == null) {
            this.mapEmployers = new HashMap<>();
        }
        this.mapEmployers = mapEmployers;
    }

    private void setMapRankedVacancies(Map<String, Set<Vacancy>> mapRankedVacancies) {
        if (mapRankedVacancies == null) {
            this.mapRankedVacancies = new HashMap<>();
        }
        this.mapRankedVacancies = mapRankedVacancies;
    }

    private void setMapRankedEmployees(Map<String, Set<String>> mapRankedEmployees) {
        if (mapRankedEmployees == null) {
            this.mapRankedEmployees = new HashMap<>();
        }
        this.mapRankedEmployees = mapRankedEmployees;
    }

    private void setLogins(Set<String> logins) {
        if (logins == null) {
            this.logins = new HashSet<>();
        }
        this.logins = logins;
    }

    public void saveDataBaseToFile(String saveDataFileName) throws IOException {
        SaveDataBaseDtoResponse data = new SaveDataBaseDtoResponse(mapEmployees, mapEmployers, mapRankedVacancies, mapRankedEmployees, logins);
        Gson gson = new Gson();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\resources\\" + saveDataFileName))) {
            writer.write(gson.toJson(data));
        }
    }

    public void createDataBase() {
        initMaps();
    }


    //Insert
    public void insertEmployee(String token, Employee employee) throws ServerException {
        if (!checkLoginExists(employee.getLogin())) {
            mapEmployees.put(token, employee);
            logins.add(employee.getLogin());
            if (employee.getSkills() != null) {
                updateMapEmployees(token, employee.getSkills());
            }
        }
    }

    private void updateMapEmployees(String token, Set<Skill> skills) {
        for (Skill skill : skills) {
            addToRankedEmployees(token, skill.getName());
        }
    }

    public void insertEmployer(String token, Employer employer) throws ServerException {
        if (!checkLoginExists(employer.getLogin())) {
            mapEmployers.put(token, employer);
            logins.add(employer.getLogin());
        }
    }

    public void insertSkill(String token, Skill skill) throws ServerException {
        checkEmployeeExists(token);
        mapEmployees.get(token).addSkill(skill);
        addToRankedEmployees(token, skill.getName());

    }

    public void insertVacancy(String token, Vacancy vacancy) throws ServerException {
        checkEmployerExists(token);
        mapEmployers.get(token).addVacancy(vacancy);
        addToRankedVacancy(vacancy);

    }


    public void insertRequirement(String token, String vacancyName, Requirement requirement) throws ServerException {
        checkEmployerExists(token);
        mapEmployers.get(token).getVacancy(vacancyName).addRequirement(requirement);
        addToRankedVacancy(mapEmployers.get(token).getVacancy(vacancyName), requirement.getName());

    }

    //Update
    public void updateEmployee(String token, Employee employee) throws ServerException {
        checkEmployeeExists(token);
        if (!employee.getLogin().equals(mapEmployees.get(token).getLogin())) {
            throw new ServerException(ServerErrorCode.PERSON_WRONG_LOGIN, employee.getLogin());
        }
        mapEmployees.put(token, employee);

    }

    public void updateSkill(String token, Skill skill) throws ServerException {
        checkEmployeeExists(token);
        mapEmployees.get(token).getSkill(skill.getName()).setScore(skill.getScore());

    }

    public void updateEmployer(String token, Employer employer) throws ServerException {
        checkEmployerExists(token);
        if (!employer.getLogin().equals(mapEmployers.get(token).getLogin())) {
            throw new ServerException(ServerErrorCode.PERSON_WRONG_LOGIN, employer.getLogin());
        }
        mapEmployers.put(token, employer);

    }

    public void updateVacancy(String token, Vacancy vacancy) throws ServerException {
        checkEmployerExists(token);
        mapEmployers.get(token).getVacancy(vacancy.getName()).setSalary(vacancy.getSalary());
    }


    public void updateVacancyActivity(String token, String vacancyName, boolean active) throws ServerException {
        checkEmployerExists(token);
        mapEmployers.get(token).getVacancy(vacancyName).setActive(active);

    }


    //Delete
    public void deleteEmployee(String token) throws ServerException {
        checkEmployeeExists(token);
        deleteFromLogins(mapEmployees.get(token).getLogin());
        deleteFromRankedEmployees(mapEmployees.get(token));
        mapEmployees.remove(token);
    }

    public void deleteSkill(String token, Skill skill) throws ServerException {
        checkSkillExists(token, skill);
        deleteFromRankedEmployees(token, skill);
        mapEmployees.get(token).removeSkill(skill);

    }


    public void deleteEmployer(String token) throws ServerException {
        checkEmployerExists(token);
        deleteFromLogins(mapEmployers.get(token).getLogin());
        deleteFromRankedVacancy(mapEmployers.get(token));
        mapEmployers.remove(token);

    }

    public void deleteVacancy(String token, String vacancyName) throws ServerException {
        checkEmployerExists(token);
        mapEmployers.get(token).removeVacancy(vacancyName);
    }

    public void deleteRequirement(String token, String vacancyName, Requirement requirement) throws ServerException {
        checkEmployerExists(token);
        mapEmployers.get(token).getVacancy(vacancyName).deleteRequirement(requirement);
    }


    //EMPLOYEE METHODS
    public Employee getEmployee(String token) throws ServerException {
        checkEmployeeExists(token);
        return mapEmployees.get(token);
    }

    public void setActivityEmployee(String token, boolean active) throws ServerException {
        checkEmployeeExists(token);
        mapEmployees.get(token).setActive(active);

    }

    public Set<Skill> getSkills(String token) throws ServerException {
        checkEmployeeExists(token);
        return mapEmployees.get(token).getSkills();
    }

    public void logOnEmployee(String token) throws ServerException {
        checkEmployeeExists(token);
        logOnEmployees.add(token);
    }

    public void logOffEmployee(String token) throws ServerException {
        checkEmployeeExists(token);
        logOnEmployees.remove(token);
    }


    //------//Selections employee
    public Set<Employee> getBestEmployees(Vacancy vacancy) throws ServerException {
        //список всех потенциальных работников, имеющих все необходимые для этой
        //вакансии умения на уровне не ниже уровня, указанного в требовании
        Set<Employee> result = new HashSet<>();
        Set<Requirement> requirements = new HashSet<>();
        requirements.addAll(vacancy.getRequirements());
        for (Requirement requirement : requirements) {
            for (String token : mapRankedEmployees.get(requirement.getName())) {
                if (containsAllSkills(requirements, getEmployee(token).getSkills(), false)) {
                    result.add(getEmployee(token));
                }
            }
        }
        return result;
    }


    public Set<Employee> getGoodEmployees(Vacancy vacancy) throws ServerException {
        //список всех потенциальных работников, имеющих все обязательные требования
        //на уровне не ниже уровня, указанного в требовании
        Set<Employee> result = new HashSet<>();
        Set<Requirement> requirements = new HashSet<>();
        requirements.addAll(vacancy.getMandatoryRequirements());
        for (Requirement requirement : requirements) {
            for (String token : mapRankedEmployees.get(requirement.getName())) {
                if (containsAllSkills(requirements, getEmployee(token).getSkills(), false)) {
                    result.add(getEmployee(token));
                }
            }
        }
        return result;
    }


    public Set<Employee> getAnyLevelEmployees(Vacancy vacancy) throws ServerException {
        //список всех потенциальных работников, имеющих все необходимые для этой
        //вакансии умения на любом уровне
        Set<Employee> result = new HashSet<>();
        Set<Requirement> requirements = new HashSet<>();
        requirements.addAll(vacancy.getRequirements());
        for (Requirement requirement : requirements) {
            for (String token : mapRankedEmployees.get(requirement.getName())) {
                if (containsAllSkills(requirements, getEmployee(token).getSkills(), true)) {
                    result.add(getEmployee(token));
                }
            }
        }
        return result;
    }


    public Set<Employee> getAsLeastOneRequirementEmployees(Vacancy vacancy) throws ServerException {
        //список всех потенциальных работников, имеющих хотя бы одно
        //необходимое для этой вакансии умение на уровне не ниже уровня, указанного в требовании
        Set<Employee> result = new HashSet<>();
        for (Requirement requirement : vacancy.getRequirements()) {
            for (String token : mapRankedEmployees.get(requirement.getName())) {
                result.add(getEmployee(token));
            }
        }
        return result;
    }

    private boolean containsAllSkills(Set<Requirement> requirements, Set<Skill> skills, boolean anyScore) {
        for (Requirement requirement : requirements) {
            if (!containsSkill(skills, requirement, anyScore)) {
                return false;
            }
        }
        return true;
    }

    private boolean containsSkill(Set<Skill> skills, Requirement requirement, boolean anyScore) {
        for (Skill skill : skills) {
            if (anyScore) {
                if (requirement.getName().equals(skill.getName())) {
                    return true;
                }
            } else {
                if (requirement.getName().equals(skill.getName()) && requirement.getScore() <= skill.getScore()) {
                    return true;
                }
            }
        }
        return false;
    }


    public Set<Vacancy> getBestVacancies(String token) throws ServerException {
        //список всех вакансий работодателей, для которых его набор умений соответствует
        // требованиям работодателя на уровне не ниже уровня, указанного в требовании
        Set<Vacancy> result = new HashSet<>();
        for (Skill skill : getSkills(token)) {
            if (mapRankedVacancies.get(skill.getName()) != null) {
                for (Vacancy vacancy : mapRankedVacancies.get(skill.getName())) {
                    if (containsAllSkills(vacancy.getRequirements(), getSkills(token), false)) {
                        result.add(vacancy);
                    }
                }
            }
        }
        return result;
    }

    public Set<Vacancy> getGoodVacancies(String token) throws ServerException {
        //список всех вакансий работодателей, для которых его набор умений соответствует обязательным
        //требованиям работодателя на уровне не ниже уровня, указанного в требовании
        Set<Vacancy> result = new HashSet<>();
        for (Skill skill : getSkills(token)) {
            if (mapRankedVacancies.get(skill.getName()) != null) {
                for (Vacancy vacancy : mapRankedVacancies.get(skill.getName())) {
                    if (containsAllSkills(vacancy.getMandatoryRequirements(), getSkills(token), false)) {
                        result.add(vacancy);
                    }
                }
            }
        }
        return result;
    }

    public Set<Vacancy> getAnyLevelVacancies(String token) throws ServerException {
        //список всех вакансий работодателей, для которых его набор умений
        //соответствует требованиям работодателя на любом уровне
        Set<Vacancy> result = new HashSet<>();
        for (Skill skill : getSkills(token)) {
            if (mapRankedVacancies.get(skill.getName()) != null) {
                for (Vacancy vacancy : mapRankedVacancies.get(skill.getName())) {
                    if (containsAllSkills(vacancy.getRequirements(), getSkills(token), true)) {
                        result.add(vacancy);
                    }
                }
            }
        }
        return result;
    }


    public Set<Vacancy> getAsLeastOneRequirementVacancies(String token) throws ServerException {
        //список всех вакансий работодателей, для которых работник имеет хотя бы одно умение из списка в требовании
        // работодателя на уровне не ниже уровня, указанного в требовании. В этом случае список выдается отсортированным
        // по числу найденных умений,то есть в начале списка приводятся те вакансии работодателей, для которых работник
        // имеет большее число умений.

        SortedMap<Integer, Set<Vacancy>> result = new TreeMap<>(Comparator.reverseOrder());
        Set<Vacancy> vacancies = new HashSet<>();
        for (Skill skill : getSkills(token)) {

            for (Vacancy vacancy : mapRankedVacancies.get(skill.getName())) {
                int count = 0;
                if (mapRankedVacancies.get(skill.getName()) != null) {
                    for (Requirement requirement : vacancy.getRequirements()) {
                        if (containsSkill(getSkills(token), requirement, false)) {
                            count++;
                        }
                    }
                    if (count != 0) {
                        if (!result.containsKey(count)) {
                            result.put(count, new HashSet<>());
                        }
                        vacancies.add(vacancy);
                        result.get(count).add(vacancy);
                    }
                }
            }
        }
        return convertMapToSet(result);
    }


    //EMPLOYER METHODS
    public Employer getEmployer(String token) throws ServerException {
        checkEmployerExists(token);
        return mapEmployers.get(token);
    }

    public Set<Vacancy> getEmployerVacancies(String token) throws ServerException {
        checkEmployerExists(token);
        return mapEmployers.get(token).getVacancies();
    }

    public void logOnEmployer(String token) throws ServerException {
        checkEmployerExists(token);
        logOnEmployers.add(token);
    }

    public void logOffEmployer(String token) throws ServerException {
        checkEmployerExists(token);
        logOnEmployers.remove(token);
    }

    public void hireEmployee(String employeeToken, String employerToken, String vacancyName) throws ServerException {
        checkEmployeeExists(employeeToken);
        mapEmployees.get(employeeToken).setActive(false);
        checkEmployerExists(employerToken);
        mapEmployers.get(employerToken).getVacancy(vacancyName).setActive(false);
    }


    private void checkEmployeeExists(String token) throws ServerException {
        if (!mapEmployees.containsKey(token)) {
            throw new ServerException(ServerErrorCode.DATABASE_EMPLOYEE_NOT_FOUND);
        }
    }

    private void checkEmployerExists(String token) throws ServerException {
        if (!mapEmployers.containsKey(token)) {
            throw new ServerException(ServerErrorCode.DATABASE_EMPLOYER_NOT_FOUND);
        }
    }

    private void checkSkillExists(String token, Skill skill) throws ServerException {
        if (!mapEmployees.get(token).getSkills().contains(skill)) {
            throw new ServerException(ServerErrorCode.EMPLOYEE_SKILL_NOT_FOUND);
        }
    }

    private Set<Vacancy> convertMapToSet(Map<Integer, Set<Vacancy>> map) {
        Set<Vacancy> result = new LinkedHashSet<>();
        for (Integer key : map.keySet()) {
            result.addAll(map.get(key));
        }
        return result;
    }

    private void addToRankedVacancy(Vacancy vacancy) {
        if (vacancy.getRequirements() != null) {
            for (Requirement requirement : vacancy.getRequirements()) {
                if (!mapRankedVacancies.containsKey(requirement.getName())) {
                    mapRankedVacancies.put(requirement.getName(), new HashSet<>());
                }
                mapRankedVacancies.get(requirement.getName()).add(vacancy);
            }
        }
    }

    private void addToRankedVacancy(Vacancy vacancy, String requirementName) {
        Set<Vacancy> set = mapRankedVacancies.get(requirementName);
        if (set == null) {
            set = new HashSet<>();
            mapRankedVacancies.put(requirementName, set);
        }
        mapRankedVacancies.get(requirementName).add(vacancy);
    }

    private void addToRankedEmployees(String token, String skillName) {
        Set<String> set = mapRankedEmployees.get(skillName);
        if (set == null) {
            set = new HashSet<>();
            mapRankedEmployees.put(skillName, set);
        }
        mapRankedEmployees.get(skillName).add(token);
    }

    private void deleteFromRankedVacancy(Employer employer) {
        for (Vacancy vacancy : employer.getVacancies()) {
            for (Requirement requirement : vacancy.getRequirements()) {
                mapRankedVacancies.get(requirement.getName()).remove(vacancy);
            }
        }
    }

    private void deleteFromRankedEmployees(Employee employee) {
        for (Skill skill : employee.getSkills()) {
            mapRankedEmployees.get(skill.getName()).remove(employee);
        }
    }

    private void deleteFromRankedEmployees(String token, Skill skill) throws ServerException {
        mapRankedEmployees.get(skill.getName()).remove(getEmployee(token));
    }

    private void deleteFromLogins(String login) {
        logins.remove(login);
    }

    private boolean checkLoginExists(String login) throws ServerException {
        if (logins.contains(login)) {
            throw new ServerException(ServerErrorCode.DATABASE_LOGIN_EXISTS, login);
        }
        return false;
    }


}
