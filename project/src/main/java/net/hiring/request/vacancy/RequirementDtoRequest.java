package net.thumbtack.school.hiring.request.vacancy;

import net.thumbtack.school.hiring.exception.ServerErrorCode;
import net.thumbtack.school.hiring.exception.ServerException;

public class RequirementDtoRequest {
    private String name;
    private int score;
    private boolean surely;

    public RequirementDtoRequest(String name, int score, boolean surely) throws ServerException {
        setName(name);
        setScore(score);
        setSurely(surely);
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public boolean isSurely() {
        return surely;
    }

    public void setName(String name) throws ServerException {
        if (name == null || name.equals("")) {
            throw new ServerException(ServerErrorCode.REQUIREMENT_WRONG_NAME);
        }
        this.name = name;
    }

    public void setScore(int score) throws ServerException {
        if (score < 1 || score > 5) {
            throw new ServerException(ServerErrorCode.REQUIREMENT_WRONG_SCORE);
        }
        this.score = score;
    }

    public void setSurely(boolean surely) {
        this.surely = surely;
    }
}
