package xyz.navinda.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.navinda.employee.entity.Employee;
import xyz.navinda.employee.service.EmployeeService;

import java.util.List;
import java.util.Optional;

/**
 * EmployeeController is a RESTful API controller for managing Employee resources.
 * It exposes endpoints for creating, retrieving and finding employees.
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Creates a new employee with the given employee object.
     *
     * @param employee the employee object to be created
     * @return the created employee object with its assigned ID
     */
    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return employeeService.create(employee);
    }

    /**
     * Retrieves a list of all employees.
     *
     * @return a list of all employees
     */
    @GetMapping
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    /**
     * Retrieves an employee with the specified ID.
     *
     * @param id the ID of the employee to be retrieved
     * @return an Optional containing the employee if found, otherwise an empty Optional
     */
    @GetMapping("/{id}")
    public Optional<Employee> findById(@PathVariable Long id) {
        return employeeService.findById(id);
    }
}
