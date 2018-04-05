package net.thumbtack.school.hiring.response.vacancy;

import net.thumbtack.school.hiring.vacancy.Vacancy;

import java.util.Set;

public class GetGoodVacanciesDtoResponse extends GetVacanciesDtoResponse {
    public GetGoodVacanciesDtoResponse(Set<Vacancy> vacancies) {
        super(vacancies);
    }
}
