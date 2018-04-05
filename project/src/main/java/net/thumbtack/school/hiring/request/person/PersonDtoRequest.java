package net.thumbtack.school.hiring.request.person;

import net.thumbtack.school.hiring.exception.ServerErrorCode;
import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.settings.Settings;

import java.util.regex.Matcher;

public class PersonDtoRequest {

    private String name;
    private String middleName;
    private String lastName;
    private String email;
    private String login;
    private String password;

    public PersonDtoRequest(String name, String middleName, String lastName, String email, String login, String password) throws ServerException {
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

    private void setName(String name) throws ServerException {
        if (name == null || name.equals("")) {
            throw new ServerException(ServerErrorCode.PERSON_EMPTY_NAME);
        }
        this.name = name;
    }

    private void setMiddleName(String middleName) {
        if (middleName == null) middleName = "";
        this.middleName = middleName;
    }

    private void setLastName(String lastName) throws ServerException {
        if (lastName == null || lastName.trim().equals("")) {
            throw new ServerException(ServerErrorCode.PERSON_EMPTY_LASTNAME);
        }
        this.lastName = lastName;
    }

    private void setEmail(String email) throws ServerException {
        if (email == null || email.trim().equals("")) {
            throw new ServerException(ServerErrorCode.PERSON_NULL_EMAIL);
        }
        if (!isValidEmailAddress(email)) {
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

    private void setPassword(String password) throws ServerException {
        if (password == null || password.trim().equals("")) {
            throw new ServerException(ServerErrorCode.PERSON_NULL_PASSWORD);
        }
        if (!isValidPassword(password)) {
            throw new ServerException(ServerErrorCode.PERSON_WRONG_PASSWORD);
        }
        this.password = password;
    }

    private static boolean isValidPassword(String password) {
        return password.length() >= Settings.PASSWORD_LENGTH;
    }
}
