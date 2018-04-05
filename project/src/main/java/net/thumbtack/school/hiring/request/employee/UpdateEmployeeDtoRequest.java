package net.thumbtack.school.hiring.request.employee;

import net.thumbtack.school.hiring.exception.ServerErrorCode;
import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.settings.Settings;
import net.thumbtack.school.hiring.vacancy.Skill;

import java.util.Set;
import java.util.regex.Matcher;

public class UpdateEmployeeDtoRequest extends BaseEmployeeDtoRequest {
    private String token;

    public UpdateEmployeeDtoRequest(String name, String middleName, String lastName, String email, String login, String password, Set<Skill> skills, String token) throws ServerException {
        super(name, middleName, lastName, email, login, password, skills);
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
