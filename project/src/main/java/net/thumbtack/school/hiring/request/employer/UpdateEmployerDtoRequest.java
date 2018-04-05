package net.thumbtack.school.hiring.request.employer;

import net.thumbtack.school.hiring.exception.ServerErrorCode;
import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.settings.Settings;
import net.thumbtack.school.hiring.vacancy.Vacancy;

import java.util.Set;
import java.util.regex.Matcher;

public class UpdateEmployerDtoRequest extends EmployerDtoRequest {
    private String token;

    public UpdateEmployerDtoRequest(String name, String middleName, String lastName, String email, String login, String password, String companyName, String address, Set<Vacancy> vacancies, String token) throws ServerException {
        super(name, middleName, lastName, email, login, password, companyName, address, vacancies);
        setToken(token);
    }

    private void setToken(String token) throws ServerException {
        if(!isValidToken(token)){
            throw new ServerException(ServerErrorCode.SERVER_WRONG_TOKEN);
        }
        this.token = token;
    }
    private static boolean isValidToken(String token) {
        Matcher matcher = Settings.VALID_TOKEN.matcher(token);
        return matcher.find();
    }
    public String getToken() {
        return token;
    }
}
