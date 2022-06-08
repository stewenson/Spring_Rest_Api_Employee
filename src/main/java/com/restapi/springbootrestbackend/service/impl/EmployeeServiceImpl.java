package com.restapi.springbootrestbackend.service.impl;

import com.restapi.springbootrestbackend.exception.ResourceNotFoundException;
import com.restapi.springbootrestbackend.model.Employee;
import com.restapi.springbootrestbackend.repository.EmployeeRepository;
import com.restapi.springbootrestbackend.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
        return employeeRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Employee", "ID", id));
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
        //Check ehwther employee with given id is exist in DB
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Employee", "ID", id));

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        // save exist employee to DB
        employeeRepository.save(existingEmployee);
        return existingEmployee;
    }

    @Override
    public void deleteEmployee(long id) {
        employeeRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Employee", "ID", id));
        employeeRepository.deleteById(id);
    }

}
