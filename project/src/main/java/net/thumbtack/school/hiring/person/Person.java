package net.thumbtack.school.hiring.person;


import net.thumbtack.school.hiring.exception.ServerErrorCode;
import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.settings.Settings;

import java.util.Objects;
import java.util.regex.Matcher;


public abstract class Person {
    private String name;
    private String middleName;
    private String lastName;
    private String email;
    private String login;
    private String password;


    public Person(String name, String middleName, String lastName, String email, String login, String password) throws ServerException {
        setName(name);
        setMiddleName(middleName);
        setLastName(lastName);
        setEmail(email);
        setLogin(login);
        setPassword(password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws ServerException {
        if (name == null || name.trim().equals("")) {
            throw new ServerException(ServerErrorCode.PERSON_EMPTY_NAME);
        }
        this.name = name;
    }

    public String getMiddleName() {
        if (middleName == null) return "";
        return middleName;
    }

    public void setMiddleName(String middleName) {
        if (middleName == null) middleName = "";
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws ServerException {
        if (lastName == null || lastName.trim().equals("")) {
            throw new ServerException(ServerErrorCode.PERSON_EMPTY_LASTNAME);
        }
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
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

    public String getLogin() {
        return login;
    }

    private void setLogin(String login) throws ServerException {
        if (login == null || login.trim().equals("")) {
            throw new ServerException(ServerErrorCode.PERSON_NULL_LOGIN);
        }
        this.login = login;
    }

    public String getPassword() {
        return password;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(middleName, person.middleName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(email, person.email) &&
                Objects.equals(login, person.login) &&
                Objects.equals(password, person.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, middleName, lastName, email, login, password);
    }
}
