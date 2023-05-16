package xyz.navinda.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.navinda.admin.entity.Department;
import xyz.navinda.admin.entity.Employee;
import xyz.navinda.admin.service.AdminService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Employee endpoints
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return adminService.createEmployee(employee).block();
    }

    @GetMapping("/employees")
    public List<Employee> findAllEmployees() {
        return adminService.findAllEmployees().block();
    }

    @GetMapping("/employees/{id}")
    public Optional<Employee> findEmployeeById(@PathVariable Long id) {
        return adminService.findEmployeeById(id).block();
    }

    // Department endpoints
    @PostMapping("/departments")
    public Department createDepartment(@RequestBody Department department) {
        return adminService.createDepartment(department).block();
    }

    @GetMapping("/departments")
    public List<Department> findAllDepartments() {
        return adminService.findAllDepartments().block();
    }

    @GetMapping("/departments/{id}")
    public Optional<Department> findDepartmentById(@PathVariable Long id) {
        return adminService.findDepartmentById(id).block();
    }
}
