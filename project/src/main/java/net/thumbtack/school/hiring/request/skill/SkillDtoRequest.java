package net.thumbtack.school.hiring.request.skill;

import net.thumbtack.school.hiring.exception.ServerErrorCode;
import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.settings.Settings;

import java.util.regex.Matcher;

public class SkillDtoRequest {

    private String name;
    private int score;
    private String token;

    public SkillDtoRequest(String name, int score, String token) throws ServerException {
        setName(name);
        setScore(score);
        setToken(token);
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String getToken() {
        return token;
    }

    private void setName(String name) throws ServerException {
        if(name == null || name.equals("")){
            throw new ServerException(ServerErrorCode.SKILL_WRONG_NAME);
        }
        this.name = name;
    }

    private void setScore(int score) throws ServerException {
        if(score < 1 || score > 5){
            throw new ServerException(ServerErrorCode.SKILL_WRONG_SCORE, String.valueOf(score));
        }
        this.score = score;
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
