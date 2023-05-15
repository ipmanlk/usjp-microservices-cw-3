package xyz.navinda.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.navinda.admin.entity.Admin;
import xyz.navinda.admin.service.AdminService;

import java.util.List;
import java.util.Optional;

/**
 * AdminController is a RESTful API controller for managing Admin resources.
 * It exposes endpoints for creating, retrieving and finding admins.
 */
@RestController
@RequestMapping("/admins")
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     * Creates a new admin with the given admin object.
     *
     * @param admin the admin object to be created
     * @return the created admin object with its assigned ID
     */
    @PostMapping
    public Admin create(@RequestBody Admin admin) {
        return adminService.create(admin);
    }

    /**
     * Retrieves a list of all admins.
     *
     * @return a list of all admins
     */
    @GetMapping
    public List<Admin> findAll() {
        return adminService.findAll();
    }

    /**
     * Retrieves an admin with the specified ID.
     *
     * @param id the ID of the admin to be retrieved
     * @return an Optional containing the admin if found, otherwise an empty Optional
     */
    @GetMapping("/{id}")
    public Optional<Admin> findById(@PathVariable Long id) {
        return adminService.findById(id);
    }
}