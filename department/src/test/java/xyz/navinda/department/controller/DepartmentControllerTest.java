package xyz.navinda.department.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import xyz.navinda.department.entity.Department;
import xyz.navinda.department.service.DepartmentService;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DepartmentController.class)
public class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DepartmentService departmentService;

    private Department department;

    @BeforeEach
    public void setUp() {
        department = new Department();
        department.setId(1L);
        department.setName("Test Department");
        department.setDescription("Test Description");
    }

    @Test
    public void createDepartment() throws Exception {
        when(departmentService.create(any(Department.class))).thenReturn(department);

        mockMvc.perform(post("/departments")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(department)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Test Department"))
                .andExpect(jsonPath("$.description").value("Test Description"));

        verify(departmentService, times(1)).create(any(Department.class));
    }

    @Test
    public void findAllDepartments() throws Exception {
        when(departmentService.findAll()).thenReturn(Arrays.asList(department));

        mockMvc.perform(get("/departments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Test Department"))
                .andExpect(jsonPath("$[0].description").value("Test Description"));

        verify(departmentService, times(1)).findAll();
    }

    @Test
    public void findDepartmentById() throws Exception {
        when(departmentService.findById(1L)).thenReturn(Optional.of(department));

        mockMvc.perform(get("/departments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Test Department"))
                .andExpect(jsonPath("$.description").value("Test Description"));

        verify(departmentService, times(1)).findById(1L);
    }
}
