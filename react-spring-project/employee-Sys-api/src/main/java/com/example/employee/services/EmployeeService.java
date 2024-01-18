package com.example.employee.services;

import java.util.List;

import com.example.employee.model.Employee;

public interface EmployeeService {

    Employee createEmployee( Employee employee);
    List<Employee> getAllEmployees();
    boolean deleteEmployee(Long id);
    Employee getEmployeeById(Long id);
    Employee updateEmloyee(Long id, Employee employee);
} 
