package com.sqd17.demoproject.controller;

import com.sqd17.demoproject.dto.EmployeeRequest;
import com.sqd17.demoproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeRequest> getEmployeeById(@PathVariable Long id){
        EmployeeRequest employeeRequest = null;
        employeeRequest = employeeService.getEmployeeById(id);

        return ResponseEntity.ok(employeeRequest);

    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeRequest> updateEmployee(@PathVariable Long id,
                                                          @RequestBody EmployeeRequest employeeRequest){
        employeeRequest = employeeService.updateEmployee(id, employeeRequest);

        return ResponseEntity.ok(employeeRequest);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
        boolean deleted = false;
        deleted = employeeService.deleteEmployee(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", deleted);

        return ResponseEntity.ok(response);
    }

}
