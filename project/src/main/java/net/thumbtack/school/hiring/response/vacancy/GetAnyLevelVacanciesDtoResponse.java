package net.thumbtack.school.hiring.response.vacancy;

import net.thumbtack.school.hiring.vacancy.Vacancy;

import java.util.Set;

public class GetAnyLevelVacanciesDtoResponse extends GetVacanciesDtoResponse {
    public GetAnyLevelVacanciesDtoResponse(Set<Vacancy> vacancies) {
        super(vacancies);
    }
}
