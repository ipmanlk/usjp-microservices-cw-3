package xyz.navinda.admin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import xyz.navinda.admin.entity.Admin;
import xyz.navinda.admin.service.AdminService;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AdminController.class)
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AdminService adminService;

    private Admin admin;

    @BeforeEach
    public void setUp() {
        admin = new Admin();
        admin.setId(1L);
        admin.setName("Test Admin");
        admin.setDescription("Test Description");
    }

    @Test
    public void createAdmin() throws Exception {
        when(adminService.create(any(Admin.class))).thenReturn(admin);

        mockMvc.perform(post("/admins")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(admin)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Test Admin"))
                .andExpect(jsonPath("$.description").value("Test Description"));

        verify(adminService, times(1)).create(any(Admin.class));
    }

    @Test
    public void findAllAdmins() throws Exception {
        when(adminService.findAll()).thenReturn(Arrays.asList(admin));

        mockMvc.perform(get("/departments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Test Admin"))
                .andExpect(jsonPath("$[0].description").value("Test Description"));

        verify(adminService, times(1)).findAll();
    }

    @Test
    public void findAdminById() throws Exception {
        when(adminService.findById(1L)).thenReturn(Optional.of(admin));

        mockMvc.perform(get("/admins/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Test Admin"))
                .andExpect(jsonPath("$.description").value("Test Description"));

        verify(adminService, times(1)).findById(1L);
    }
}
