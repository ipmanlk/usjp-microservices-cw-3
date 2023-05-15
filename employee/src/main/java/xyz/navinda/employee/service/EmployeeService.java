package xyz.navinda.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.navinda.employee.entity.Employee;
import xyz.navinda.employee.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

/**
 * EmployeeService is a service class responsible for handling business logic related to Employee resources.
 * It provides methods for creating, retrieving and finding employees.
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Creates a new employee with the given employee object.
     *
     * @param employee the employee object to be created
     * @return the created employee object with its assigned ID
     */
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    /**
     * Retrieves a list of all employees.
     *
     * @return a list of all employees
     */
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    /**
     * Retrieves an employee with the specified ID.
     *
     * @param id the ID of the employee to be retrieved
     * @return an Optional containing the employee if found, otherwise an empty Optional
     */
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }
}
