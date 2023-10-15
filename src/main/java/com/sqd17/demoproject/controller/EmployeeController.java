package com.sqd17.demoproject.controller;

import com.sqd17.demoproject.dto.EmployeeRequest;
import com.sqd17.demoproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5173")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employees")
    public EmployeeRequest createEmployee(@RequestBody EmployeeRequest employeeDto){
        return employeeService.createEmployee(employeeDto);
    }

    @GetMapping("/employees")
    public List<EmployeeRequest> getAllEmployees(){
        return employeeService.getAllEmployee();
    }

}
