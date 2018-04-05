package net.thumbtack.school.hiring.request.employer;

import net.thumbtack.school.hiring.exception.ServerErrorCode;
import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.settings.Settings;
import net.thumbtack.school.hiring.vacancy.Vacancy;

import java.util.Set;
import java.util.regex.Matcher;

public class DeleteEmployerDtoRequest {
    private String token;

    public DeleteEmployerDtoRequest(String token) throws ServerException {
        setToken(token);
    }

    public String getToken() {
        return token;
    }

    private void setToken(String token) throws ServerException {
        if (!isValidToken(token)) {
            throw new ServerException(ServerErrorCode.SERVER_WRONG_TOKEN);
        }
        this.token = token;
    }

    private static boolean isValidToken(String token) {
        Matcher matcher = Settings.VALID_TOKEN.matcher(token);
        return matcher.find();
    }
}
