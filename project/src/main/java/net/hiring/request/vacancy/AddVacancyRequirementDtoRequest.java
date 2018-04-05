package net.thumbtack.school.hiring.request.vacancy;


import net.thumbtack.school.hiring.exception.ServerErrorCode;
import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.settings.Settings;

import java.util.regex.Matcher;

public class AddVacancyRequirementDtoRequest extends RequirementDtoRequest {
    private String vacancyName;
    private String token;

    public AddVacancyRequirementDtoRequest(String name, int score, boolean surely, String token, String vacancyName) throws ServerException {
        super(name, score, surely);
        setToken(token);
        setVacancyName(vacancyName);
    }


    public String getVacancyName() {
        return vacancyName;
    }

    public String getToken() {
        return token;
    }

    private void setVacancyName(String vacancyName) throws ServerException {
        if(vacancyName == null || vacancyName.equals("")){
            throw new ServerException(ServerErrorCode.REQUEST_NULL_FIELD);
        }
        this.vacancyName = vacancyName;
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
