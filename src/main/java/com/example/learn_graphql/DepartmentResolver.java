package com.example.learn_graphql;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.learn_graphql.entity.Department;
import com.example.learn_graphql.entity.Employee;
import com.example.learn_graphql.repo.DepartmentRepository;
import com.example.learn_graphql.repo.EmployeeRepository;

import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class GraphQLResolver implements GraphQLQueryResolver<Department> {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    GraphQLScalarType longScalar = ExtendedScalars.newAliasedScalar("Long")
            .aliasedScalar(ExtendedScalars.GraphQLLong)
            .build();

    public List<Employee> employees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> employee(Long id) {
        return employeeRepository.findById(id);
    }

    public List<Department> departments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> department(Long id) {
        return departmentRepository.findById(id);
    }
}
