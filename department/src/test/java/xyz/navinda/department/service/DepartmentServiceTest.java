package xyz.navinda.department.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import xyz.navinda.department.entity.Department;
import xyz.navinda.department.repository.DepartmentRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @InjectMocks
    private DepartmentService departmentService;

    @Mock
    private DepartmentRepository departmentRepository;

    private Department department;

    @BeforeEach
    public void setUp() {
        department = new Department();
        department.setId(1L);
        department.setName("Test Department");
        department.setDescription("Test Description");
    }

    @Test
    public void createDepartment() {
        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        Department createdDepartment = departmentService.create(department);

        assertEquals(1L, createdDepartment.getId());
        assertEquals("Test Department", createdDepartment.getName());
        assertEquals("Test Description", createdDepartment.getDescription());

        verify(departmentRepository, times(1)).save(any(Department.class));
    }

    @Test
    public void findAllDepartments() {
        when(departmentRepository.findAll()).thenReturn(Arrays.asList(department));

        List<Department> departments = departmentService.findAll();

        assertEquals(1, departments.size());
        assertEquals(1L, departments.get(0).getId());
        assertEquals("Test Department", departments.get(0).getName());
        assertEquals("Test Description", departments.get(0).getDescription());

        verify(departmentRepository, times(1)).findAll();
    }

    @Test
    public void findDepartmentById() {
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));

        Optional<Department> foundDepartment = departmentService.findById(1L);

        assertTrue(foundDepartment.isPresent());
        assertEquals(1L, foundDepartment.get().getId());
        assertEquals("Test Department", foundDepartment.get().getName());
        assertEquals("Test Description", foundDepartment.get().getDescription());

        verify(departmentRepository, times(1)).findById(1L);
    }
}
