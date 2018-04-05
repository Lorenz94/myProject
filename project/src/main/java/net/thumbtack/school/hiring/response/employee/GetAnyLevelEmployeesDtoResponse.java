package net.thumbtack.school.hiring.response.employee;

import net.thumbtack.school.hiring.person.Employee;

import java.util.Set;

public class GetAnyLevelEmployeesDtoResponse extends GetEmployeesDtoResponse {
    public GetAnyLevelEmployeesDtoResponse(Set<Employee> employees) {
        super(employees);
    }
}
