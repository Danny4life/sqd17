package com.sqd17.demoproject.service;

import com.sqd17.demoproject.dto.EmployeeRequest;

import java.util.List;

public interface EmployeeService {

    EmployeeRequest createEmployee(EmployeeRequest employeeDto);

    List<EmployeeRequest> getAllEmployee();

    EmployeeRequest getEmployeeById(Long id);

    EmployeeRequest updateEmployee(Long id, EmployeeRequest employeeRequest);

    boolean deleteEmployee(Long id);
}
