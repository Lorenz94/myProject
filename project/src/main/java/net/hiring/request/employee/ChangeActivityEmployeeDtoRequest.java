package net.thumbtack.school.hiring.request.employee;

import net.thumbtack.school.hiring.exception.ServerErrorCode;
import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.settings.Settings;

import java.util.regex.Matcher;

public class ChangeActivityEmployeeDtoRequest {
    private String token;
    private boolean isActive;

    public ChangeActivityEmployeeDtoRequest(String token, boolean isActive) throws ServerException {
        setToken(token);
        this.isActive = isActive;
    }

    public String getToken() {
        return token;
    }

    public boolean isActive() {
        return isActive;
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
}
