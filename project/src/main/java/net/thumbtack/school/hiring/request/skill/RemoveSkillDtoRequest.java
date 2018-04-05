package net.thumbtack.school.hiring.request.skill;

import net.thumbtack.school.hiring.exception.ServerException;


public class RemoveSkillDtoRequest extends SkillDtoRequest {

    public RemoveSkillDtoRequest(String name, int score, String token) throws ServerException {
        super(name, score, token);
    }
}
