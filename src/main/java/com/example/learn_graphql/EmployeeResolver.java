package com.example.learn_graphql;

import com.example.learn_graphql.entity.Department;
import com.example.learn_graphql.entity.DepartmentInput;
import com.example.learn_graphql.entity.Employee;
import com.example.learn_graphql.entity.EmployeeInput;
import com.example.learn_graphql.repo.DepartmentRepository;
import com.example.learn_graphql.repo.EmployeeRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MutationResolver implements GraphQLMutationResolver {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public Employee createEmployee(EmployeeInput employeeInput) {
        Employee employee = new Employee();
        employee.setName(employeeInput.getName());
        employee.setEmail(employeeInput.getEmail());
        employee.setAddress(employeeInput.getAddress());
        if (employeeInput.getDepartmentId() != null) {
            employee.setDepartment(departmentRepository.findById(employeeInput.getDepartmentId()).orElse(null));
        }
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, EmployeeInput employeeInput) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid employee ID"));
        employee.setName(employeeInput.getName());
        employee.setEmail(employeeInput.getEmail());
        employee.setAddress(employeeInput.getAddress());
        if (employeeInput.getDepartmentId() != null) {
            employee.setDepartment(departmentRepository.findById(employeeInput.getDepartmentId()).orElse(null));
        }
        return employeeRepository.save(employee);
    }

    public Employee deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid employee ID"));
        employeeRepository.delete(employee);
        return employee;
    }

    public Department createDepartment(DepartmentInput departmentInput) {
        Department department = new Department();
        department.setName(departmentInput.getName());
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Long id, DepartmentInput departmentInput) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid department ID"));
        department.setName(departmentInput.getName());
        return departmentRepository.save(department);
    }

    public Department deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid department ID"));
        departmentRepository.delete(department);
        return department;
    }
}