package com.sqd17.demoproject.service.impl;

import com.sqd17.demoproject.dto.EmployeeRequest;
import com.sqd17.demoproject.entity.EmployeeEntity;
import com.sqd17.demoproject.repository.EmployeeRepository;
import com.sqd17.demoproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeRequest createEmployee(EmployeeRequest employeeDto) {

        EmployeeEntity employeeEntity = EmployeeEntity.builder()
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .email(employeeDto.getEmail())
                .build();

        employeeRepository.save(employeeEntity);

        return employeeDto;
    }

    @Override
    public List<EmployeeRequest> getAllEmployee() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();

        List<EmployeeRequest> employeeRequests = employeeEntities
                .stream()
                .map(emp -> new EmployeeRequest(
                        emp.getFirstName(), emp.getLastName(), emp.getEmail()))
                .collect(Collectors.toList());


        return employeeRequests;
    }
}
