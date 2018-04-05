package net.thumbtack.school.hiring.response.employee;

import net.thumbtack.school.hiring.exception.ServerErrorCode;
import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.settings.Settings;
import net.thumbtack.school.hiring.vacancy.Skill;

import java.util.Set;
import java.util.regex.Matcher;

public class EmployeeDtoResponse {

    private String name;
    private String middleName;
    private String lastName;
    private String email;
    private String login;
    private String password;
    private Set<Skill> skills;
    private boolean isActive;

    public EmployeeDtoResponse(String name, String middleName, String lastName, String email, String login, String password, Set<Skill> skills, boolean active) throws ServerException {
        setName(name);
        setMiddleName(middleName);
        setLastName(lastName);
        setEmail(email);
        setLogin(login);
        setPassword(password);
        setSkills(skills);
        setActive(active);
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

    public Set<Skill> getSkills() {
        return skills;
    }

    public boolean isActive() {
        return isActive;
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


    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
