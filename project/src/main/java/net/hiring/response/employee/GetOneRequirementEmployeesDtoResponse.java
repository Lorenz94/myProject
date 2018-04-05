package net.thumbtack.school.hiring.response.employee;

import net.thumbtack.school.hiring.person.Employee;

import java.util.Set;

public class GetOneRequirementEmployeesDtoResponse extends GetEmployeesDtoResponse {
    public GetOneRequirementEmployeesDtoResponse(Set<Employee> employees) {
        super(employees);
    }
}
