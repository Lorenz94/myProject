package net.thumbtack.school.hiring.vacancy;

import net.thumbtack.school.hiring.exception.ServerErrorCode;
import net.thumbtack.school.hiring.exception.ServerException;

import java.util.Objects;

public class Skill {
    private String name;
    private int score;


    public Skill(String name, int score) throws ServerException {
        setName(name);
        setScore(score);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws ServerException {
        if(name == null || name.equals("")){
            throw new ServerException(ServerErrorCode.SKILL_WRONG_NAME);
        }
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) throws ServerException {
        if(score < 1 || score > 5){
            throw new ServerException(ServerErrorCode.SKILL_WRONG_SCORE, String.valueOf(score));
        }
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return score == skill.score &&
                Objects.equals(name, skill.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, score);
    }
}
