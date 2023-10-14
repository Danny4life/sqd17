package com.sqd17.demoproject.service.impl;

import com.sqd17.demoproject.dto.EmployeeDto;
import com.sqd17.demoproject.entity.EmployeeEntity;
import com.sqd17.demoproject.repository.EmployeeRepository;
import com.sqd17.demoproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        EmployeeEntity employeeEntity = EmployeeEntity.builder()
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .email(employeeDto.getEmail())
                .build();

        employeeRepository.save(employeeEntity);

        return employeeDto;
    }
}
