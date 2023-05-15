package xyz.navinda.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.navinda.admin.entity.Admin;
import xyz.navinda.admin.repository.AdminRepository;

import java.util.List;
import java.util.Optional;

/**
 * DepartmentService is a service class responsible for handling business logic related to Department resources.
 * It provides methods for creating, retrieving and finding departments.
 */
@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    /**
     * Creates a new department with the given department object.
     *
     * @param admin the department object to be created
     * @return the created department object with its assigned ID
     */
    public Admin create(Admin admin) {
        return adminRepository.save(admin);
    }

    /**
     * Retrieves a list of all departments.
     *
     * @return a list of all departments
     */
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    /**
     * Retrieves a department with the specified ID.
     *
     * @param id the ID of the department to be retrieved
     * @return an Optional containing the department if found, otherwise an empty Optional
     */
    public Optional<Admin> findById(Long id) {
        return adminRepository.findById(id);
    }

}
