package net.thumbtack.school.hiring.exception;

public enum ServerErrorCode {
    SERVER_FILE_NOT_FOUND("File %s isn't found. If it is necessary for you to create default file, just leave the field empty."),
    SERVER_FILE_IS_NULL("File can't be null"),

    SERVER_IS_OFF("Can't %s. Server is off."),
    SERVER_EMPLOYEE_NOT_FOUND("employee with this token is not found"),
    SERVER_EMPLOYER_NOT_FOUND("Employer with this token is not found"),
    SERVER_WRONG_TOKEN("Token is wrong"),

    VACANCY_WRONG_NAME("Position name can't be null"),
    VACANCY_NULL_REQUIREMENTS("Requirements can't be null"),
    VACANCY_WRONG_TOKEN("Token is wrong"),
    VACANCY_IS_NULL("Vacancy is null"),

    SKILL_IS_NULL("Skill is null"),
    SKILL_WRONG_NAME("Skill name can't be null"),
    SKILL_WRONG_SCORE("Score must be from 1 to 5. Your score = %s"),

    REQUIREMENT_WRONG_NAME("Requirement name can't be null"),
    REQUIREMENT_WRONG_SCORE("Score must be from 1 to 5. Your score = %s"),
    REQUIREMENT_NOT_FOUND("Requirement %s is not found"),

    PERSON_EMPTY_NAME("Field name is empty"),
    PERSON_EMPTY_LASTNAME("Field last name is empty"),
    PERSON_NULL_EMAIL("Field email is empty"),
    PERSON_WRONG_EMAIL("Email %s incorrect"),
    PERSON_NULL_LOGIN("Field login is empty"),
    PERSON_WRONG_LOGIN("Login %s is impossible to change"),
    PERSON_LOGIN_EXISTS("This login '%s' already exists"),
    PERSON_NULL_PASSWORD("Field password is empty"),
    PERSON_WRONG_PASSWORD("Password has to have not less than 6 symbols"),

    EMPLOYEE_SKILL_NOT_FOUND("Skill %s is not found"),

    EMPLOYER_WRONG_NULL_COMPANYNAME("Field company name is empty"),
    EMPLOYER_WRONG_NULL_ADDRESS("Field address is empty"),
    EMPLOYER_VACANCY_NOT_FOUND("Vacancy %s is not found"),

    DATABASE_LOGIN_EXISTS("Login %s already exists"),
    DATABASE_EMPLOYEE_NOT_FOUND("employee with this token is not found"),
    DATABASE_EMPLOYER_NOT_FOUND("Employer with this token is not found"),

    REQUEST_NULL_FIELD("Field is null");


    private String errorString;

    ServerErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorCode() {
        return errorString;
    }
}