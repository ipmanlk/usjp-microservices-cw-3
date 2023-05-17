package xyz.navinda.admin.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import xyz.navinda.admin.entity.Department;
import xyz.navinda.admin.entity.Employee;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private EurekaClient discoveryClient;

    // RestTemplate to make HTTP requests
    private RestTemplate restTemplate = new RestTemplate();

    // Employee endpoints

    // Create a new Employee
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        // Discover the Employee service
        InstanceInfo instanceInfo = discoveryClient.getNextServerFromEureka("EMPLOYEE-SERVICE",false);
        // Construct the URL for the Employee service
        String empUrl = instanceInfo.getHomePageUrl() + "/employees";
        // Send a POST request to the Employee service to create a new employee
        return restTemplate.postForObject(empUrl,employee, Employee.class);
    }

    // Retrieve all Employees
    @GetMapping("/employees")
    public List<Employee> findAllEmployees() {
        InstanceInfo instanceInfo = discoveryClient.getNextServerFromEureka("EMPLOYEE-SERVICE",false);
        String empUrl = instanceInfo.getHomePageUrl() + "/employees";
        // Send a GET request to the Employee service to retrieve all employees
        Employee[] employees = restTemplate.getForObject(empUrl, Employee[].class);
        return Arrays.asList(employees);
    }

    // Retrieve an Employee by ID
    @GetMapping("/employees/{id}")
    public Employee findEmployeeById(@PathVariable Long id) {
        InstanceInfo instanceInfo = discoveryClient.getNextServerFromEureka("EMPLOYEE-SERVICE",false);
        String empUrl = instanceInfo.getHomePageUrl() + "/employees/" + id;
        // Send a GET request to the Employee service to retrieve an employee by ID
        return restTemplate.getForObject(empUrl, Employee.class);
    }

    // Department endpoints

    // Create a new Department
    @PostMapping("/departments")
    public Department createDepartment(@RequestBody Department department) {
        // Discover the Department service
        InstanceInfo instanceInfo = discoveryClient.getNextServerFromEureka("DEPARTMENT-SERVICE",false);
        // Construct the URL for the Department service
        String depUrl = instanceInfo.getHomePageUrl() + "/departments";
        // Send a POST request to the Department service to create a new department
        return restTemplate.postForObject(depUrl,department, Department.class);
    }

    // Retrieve all Departments
    @GetMapping("/departments")
    public List<Department> findAllDepartments() {
        InstanceInfo instanceInfo = discoveryClient.getNextServerFromEureka("DEPARTMENT-SERVICE",false);
        String depUrl = instanceInfo.getHomePageUrl() + "/departments";
        // Send a GET request to the Department service to retrieve all departments
        Department[] departments = restTemplate.getForObject(depUrl, Department[].class);
        return Arrays.asList(departments);
    }

    // Retrieve a Department by ID
    @GetMapping("/departments/{id}")
    public Department findDepartmentById(@PathVariable Long id) {
        InstanceInfo instanceInfo = discoveryClient.getNextServerFromEureka("DEPARTMENT-SERVICE",false);
        String depUrl = instanceInfo.getHomePageUrl() + "/departments/" + id;
        // Send a GET request to the Department service to retrieve a department by ID
        return restTemplate.getForObject(depUrl, Department.class);
    }
}
