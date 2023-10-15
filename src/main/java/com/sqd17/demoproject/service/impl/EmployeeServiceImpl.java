package com.sqd17.demoproject.service.impl;

import com.sqd17.demoproject.dto.EmployeeRequest;
import com.sqd17.demoproject.entity.EmployeeEntity;
import com.sqd17.demoproject.repository.EmployeeRepository;
import com.sqd17.demoproject.service.EmployeeService;
import org.springframework.beans.BeanUtils;
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
                .map(emp -> new EmployeeRequest(emp.getId(),
                        emp.getFirstName(), emp.getLastName(), emp.getEmail()))
                .collect(Collectors.toList());


        return employeeRequests;
    }

    @Override
    public EmployeeRequest getEmployeeById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        EmployeeRequest employeeRequest = new EmployeeRequest();
        BeanUtils.copyProperties(employeeEntity, employeeRequest);

        return employeeRequest;
    }

    @Override
    public EmployeeRequest updateEmployee(Long id, EmployeeRequest employeeRequest) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        employeeEntity.setFirstName(employeeRequest.getFirstName());
        employeeEntity.setLastName(employeeRequest.getLastName());
        employeeEntity.setEmail(employeeRequest.getEmail());

        employeeRepository.save(employeeEntity);

        return employeeRequest;
    }

    @Override
    public boolean deleteEmployee(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();

        employeeRepository.delete(employeeEntity);

        return true;
    }
}
