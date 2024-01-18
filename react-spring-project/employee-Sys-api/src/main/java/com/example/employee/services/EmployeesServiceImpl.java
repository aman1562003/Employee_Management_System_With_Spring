package com.example.employee.services;

import java.util.*;
import java.util.stream.Collectors;


import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.employee.entity.EmployeeEntity;
import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;

@Service
public class EmployeesServiceImpl implements EmployeeService {
    
    private EmployeeRepository employeeRepository;
    public EmployeesServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }




    @Override
    public Employee createEmployee( Employee employee){
        EmployeeEntity employeeEntity = new EmployeeEntity();
        
        BeanUtils.copyProperties(employee, employeeEntity);
        employeeRepository.save(employeeEntity);
        return null;
    }




    @Override
    public List<Employee> getAllEmployees() {
        
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();

        List<Employee> employees = employeeEntities
        .stream()
        .map(emp -> new Employee(emp.getId(), emp.getFirstname(), emp.getLastname(), emp.getEmail()))
        .collect(Collectors.toList());

        return employees;
        }




    @Override
    public boolean deleteEmployee(Long id) {
       
        EmployeeEntity employee = employeeRepository.findById(id).get();

        employeeRepository.delete(employee);
        return true;

    }




    @Override
    public Employee getEmployeeById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeEntity, employee);
        return employee;
    }




    @Override
    public Employee updateEmloyee(Long id, Employee employee) {

        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        employeeEntity.setFirstname(employee.getFirstname());
        employeeEntity.setLastname(employee.getLastname());
        employeeEntity.setEmail(employee.getEmail());
        employeeRepository.save(employeeEntity);
        return employee;
    }
}
