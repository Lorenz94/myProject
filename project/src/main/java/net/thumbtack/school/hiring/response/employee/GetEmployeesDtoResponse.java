package net.thumbtack.school.hiring.response.employee;

import net.thumbtack.school.hiring.person.Employee;

import java.util.Set;

public class GetEmployeesDtoResponse {
    private Set<Employee> employees;

    public GetEmployeesDtoResponse(Set<Employee> employees) {
        this.employees = employees;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

}
