package xyz.navinda.employee.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import xyz.navinda.employee.entity.Employee;
import xyz.navinda.employee.service.EmployeeService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void testCreateEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setFirstName("Kasun");
        employee.setLastName("Mendis");
        employee.setDepartmentId(1L);

        when(employeeService.create(any(Employee.class))).thenReturn(employee);

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Kasun"))
                .andExpect(jsonPath("$.lastName").value("Mendis"))
                .andExpect(jsonPath("$.departmentId").value(1L));
    }

    @Test
    public void testFindAllEmployees() throws Exception {
        Employee employee1 = new Employee();
        employee1.setFirstName("Kasun");
        employee1.setLastName("Mendis");
        employee1.setDepartmentId(1L);

        Employee employee2 = new Employee();
        employee2.setFirstName("Nimal");
        employee2.setLastName("Chamara");
        employee2.setDepartmentId(2L);

        List<Employee> employees = Arrays.asList(employee1, employee2);

        when(employeeService.findAll()).thenReturn(employees);

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Kasun"))
                .andExpect(jsonPath("$[0].lastName").value("Mendis"))
                .andExpect(jsonPath("$[0].departmentId").value(1L))
                .andExpect(jsonPath("$[1].firstName").value("Nimal"))
                .andExpect(jsonPath("$[1].lastName").value("Chamara"))
                .andExpect(jsonPath("$[1].departmentId").value(2L));
    }

    @Test
    public void testFindEmployeeById() throws Exception {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("Kasun");
        employee.setLastName("Mendis");
        employee.setDepartmentId(1L);

        when(employeeService.findById(1L)).thenReturn(Optional.of(employee));

        mockMvc.perform(get("/employees/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("Kasun"))
                .andExpect(jsonPath("$.lastName").value("Mendis"))
                .andExpect(jsonPath("$.departmentId").value(1L));
    }
}
