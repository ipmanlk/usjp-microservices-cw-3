package xyz.navinda.admin.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import xyz.navinda.admin.entity.Admin;
import xyz.navinda.admin.repository.AdminRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    @InjectMocks
    private AdminService adminService;

    @Mock
    private AdminRepository adminRepository;

    private Admin admin;

    @BeforeEach
    public void setUp() {
        admin = new Admin();
        admin.setId(1L);
        admin.setName("Test Admin");
        admin.setDescription("Test Description");
    }

    @Test
    public void createDepartment() {
        when(adminRepository.save(any(Admin.class))).thenReturn(admin);

        Admin createdAdmin = adminService.create(admin);

        assertEquals(1L, createdAdmin.getId());
        assertEquals("Test Admin", createdAdmin.getName());
        assertEquals("Test Description", createdAdmin.getDescription());

        verify(adminRepository, times(1)).save(any(Admin.class));
    }

    @Test
    public void findAllDepartments() {
        when(adminRepository.findAll()).thenReturn(Arrays.asList(admin));

        List<Admin> admins = adminService.findAll();

        assertEquals(1, admins.size());
        assertEquals(1L, admins.get(0).getId());
        assertEquals("Test Admin", admins.get(0).getName());
        assertEquals("Test Description", admins.get(0).getDescription());

        verify(adminRepository, times(1)).findAll();
    }

    @Test
    public void findDepartmentById() {
        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));

        Optional<Admin> foundDepartment = adminService.findById(1L);

        assertTrue(foundDepartment.isPresent());
        assertEquals(1L, foundDepartment.get().getId());
        assertEquals("Test Admin", foundDepartment.get().getName());
        assertEquals("Test Description", foundDepartment.get().getDescription());

        verify(adminRepository, times(1)).findById(1L);
    }
}
