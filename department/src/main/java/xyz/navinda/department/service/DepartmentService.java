package xyz.navinda.department.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.navinda.department.entity.Department;
import xyz.navinda.department.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

/**
 * DepartmentService is a service class responsible for handling business logic related to Department resources.
 * It provides methods for creating, retrieving and finding departments.
 */
@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     * Creates a new department with the given department object.
     *
     * @param department the department object to be created
     * @return the created department object with its assigned ID
     */
    public Department create(Department department) {
        return departmentRepository.save(department);
    }

    /**
     * Retrieves a list of all departments.
     *
     * @return a list of all departments
     */
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    /**
     * Retrieves a department with the specified ID.
     *
     * @param id the ID of the department to be retrieved
     * @return an Optional containing the department if found, otherwise an empty Optional
     */
    public Optional<Department> findById(Long id) {
        return departmentRepository.findById(id);
    }

}
