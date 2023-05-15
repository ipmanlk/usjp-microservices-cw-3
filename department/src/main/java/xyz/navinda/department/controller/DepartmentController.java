package xyz.navinda.department.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.navinda.department.entity.Department;
import xyz.navinda.department.service.DepartmentService;

import java.util.List;
import java.util.Optional;

/**
 * DepartmentController is a RESTful API controller for managing Department resources.
 * It exposes endpoints for creating, retrieving and finding departments.
 */
@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * Creates a new department with the given department object.
     *
     * @param department the department object to be created
     * @return the created department object with its assigned ID
     */
    @PostMapping
    public Department create(@RequestBody Department department) {
        return departmentService.create(department);
    }

    /**
     * Retrieves a list of all departments.
     *
     * @return a list of all departments
     */
    @GetMapping
    public List<Department> findAll() {
        return departmentService.findAll();
    }

    /**
     * Retrieves a department with the specified ID.
     *
     * @param id the ID of the department to be retrieved
     * @return an Optional containing the department if found, otherwise an empty Optional
     */
    @GetMapping("/{id}")
    public Optional<Department> findById(@PathVariable Long id) {
        return departmentService.findById(id);
    }
}