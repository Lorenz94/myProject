package net.thumbtack.school.hiring.response.vacancy;

import net.thumbtack.school.hiring.vacancy.Vacancy;

import java.util.Set;

public class GetBestVacanciesDtoResponse extends GetVacanciesDtoResponse {

    public GetBestVacanciesDtoResponse(Set<Vacancy> vacancies) {
        super(vacancies);
    }
}
