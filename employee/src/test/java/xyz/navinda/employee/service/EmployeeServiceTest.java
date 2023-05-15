package xyz.navinda.employee.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import xyz.navinda.employee.entity.Employee;
import xyz.navinda.employee.repository.EmployeeRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Test
    public void testCreateEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("Kasun");
        employee.setLastName("Mendis");
        employee.setDepartmentId(1L);

        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee created = employeeService.create(employee);

        assertEquals(employee.getFirstName(), created.getFirstName());
        assertEquals(employee.getLastName(), created.getLastName());
        assertEquals(employee.getDepartmentId(), created.getDepartmentId());

        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    public void testFindAllEmployees() {
        Employee employee1 = new Employee();
        employee1.setFirstName("Kasun");
        employee1.setLastName("Mendis");
        employee1.setDepartmentId(1L);

        Employee employee2 = new Employee();
        employee2.setFirstName("Nimal");
        employee2.setLastName("Chamara");
        employee2.setDepartmentId(2L);

        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee1, employee2));

        List<Employee> employees = employeeService.findAll();

        assertEquals(2, employees.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    public void testFindEmployeeById() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("Kasun");
        employee.setLastName("Mendis");
        employee.setDepartmentId(1L);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Optional<Employee> found = employeeService.findById(1L);

        assertEquals(employee.getId(), found.get().getId());
        assertEquals(employee.getFirstName(), found.get().getFirstName());
        assertEquals(employee.getLastName(), found.get().getLastName());
        assertEquals(employee.getDepartmentId(), found.get().getDepartmentId());

        verify(employeeRepository, times(1)).findById(1L);
    }
}