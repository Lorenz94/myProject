package net.thumbtack.school.hiring.response.employee;

import net.thumbtack.school.hiring.person.Employee;

import java.util.Set;

public class GetGoodEmployeesDtoResponse extends GetEmployeesDtoResponse {
    public GetGoodEmployeesDtoResponse(Set<Employee> employees) {
        super(employees);
    }
}
