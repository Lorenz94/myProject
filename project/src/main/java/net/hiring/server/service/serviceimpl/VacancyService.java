package net.thumbtack.school.hiring.server.service.serviceimpl;

import net.thumbtack.school.hiring.exception.ServerException;

public interface VacancyService {

    String addVacancy(String requestJsonString) throws ServerException;
    String removeVacancy(String requestJsonString) throws ServerException;
    String addVacancyRequirement(String requestJsonString) throws ServerException;
    String removeVacancyRequirement(String requestJsonString) throws ServerException;
    String setVacancyActivity(String requestJsonString) throws ServerException;
}
