package net.thumbtack.school.hiring.server.service.serviceimpl;

import net.thumbtack.school.hiring.exception.ServerException;

public interface SkillService {
    String addSkill(String requestJsonString) throws ServerException;
    String removeSkill(String requestJsonString) throws ServerException;
    String getSkills(String requestJsonString) throws ServerException;
    String changeSkillScore(String requestJsonString) throws ServerException;
}
