package net.thumbtack.school.hiring.response.vacancy;

import net.thumbtack.school.hiring.vacancy.Vacancy;

import java.util.HashSet;
import java.util.Set;

public class GetVacanciesDtoResponse {
    private Set<Vacancy> vacancies;

    public GetVacanciesDtoResponse(Set<Vacancy> vacancies) {
        setVacancies(vacancies);
    }

    public Set<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setVacancies(Set<Vacancy> vacancies) {
        if(vacancies == null){
            this.vacancies = new HashSet<>();
        }else{
            this.vacancies = vacancies;
        }
    }
}
