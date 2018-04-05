package net.thumbtack.school.hiring.request.employer;

import net.thumbtack.school.hiring.exception.ServerErrorCode;
import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.settings.Settings;

import java.util.regex.Matcher;

public class HireEmployeeDtoRequest {
    private String employeeToken;
    private String employerToken;
    private String vacancyName;

    public HireEmployeeDtoRequest(String employeeToken, String employerToken, String vacancyName) throws ServerException {
        setEmployeeToken(employeeToken);
        setEmployerToken(employerToken);
        setVacancyName(vacancyName);
    }

    public String getEmployeeToken() {
        return employeeToken;
    }

    public String getEmployerToken() {
        return employerToken;
    }

    public String getVacancyName() {
        return vacancyName;
    }

    private void setEmployeeToken(String employeeToken) throws ServerException {
        if (!isValidToken(employeeToken)) {
            throw new ServerException(ServerErrorCode.SERVER_WRONG_TOKEN);
        }
        this.employeeToken = employeeToken;
    }

    private static boolean isValidToken(String token) {
        Matcher matcher = Settings.VALID_TOKEN.matcher(token);
        return matcher.find();
    }

    public void setEmployerToken(String employerToken) throws ServerException {
        if (!isValidToken(employerToken)) {
            throw new ServerException(ServerErrorCode.SERVER_WRONG_TOKEN);
        }
        this.employerToken = employerToken;
    }

    public void setVacancyName(String vacancyName) throws ServerException {
        if(vacancyName == null || vacancyName.equals("")){
            throw new ServerException(ServerErrorCode.REQUEST_NULL_FIELD);
        }
        this.vacancyName = vacancyName;
    }
}
