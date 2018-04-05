package net.thumbtack.school.hiring.response.employee;

import net.thumbtack.school.hiring.person.Employee;

import java.util.Set;

public class GetBestEmployeesDtoResponse extends GetEmployeesDtoResponse {

    public GetBestEmployeesDtoResponse(Set<Employee> employees) {
        super(employees);
    }
}
