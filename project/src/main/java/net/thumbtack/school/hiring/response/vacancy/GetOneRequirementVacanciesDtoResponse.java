package net.thumbtack.school.hiring.response.vacancy;

import net.thumbtack.school.hiring.vacancy.Vacancy;

import java.util.Set;

public class GetOneRequirementVacanciesDtoResponse extends GetVacanciesDtoResponse {
    public GetOneRequirementVacanciesDtoResponse(Set<Vacancy> vacancies) {
        super(vacancies);
    }
}
