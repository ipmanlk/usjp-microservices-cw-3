package xyz.navinda.admin.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import xyz.navinda.admin.entity.Department;
import xyz.navinda.admin.entity.Employee;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private final WebClient employeeWebClient;
    private final WebClient departmentWebClient;

    @Autowired
    public AdminService(WebClient.Builder webClientBuilder) {
        this.employeeWebClient = webClientBuilder.baseUrl("http://localhost:3002").build(); // Employee Service
        this.departmentWebClient = webClientBuilder.baseUrl("http://localhost:3001").build(); // Department Service
    }

    // Methods to interact with the Employee Service
    @CircuitBreaker(name = "employee", fallbackMethod = "fallbackForEmployeeService")
    public Mono<Employee> createEmployee(Employee employee) {
        return this.employeeWebClient.post()
                .uri("/employees")
                .body(Mono.just(employee), Employee.class)
                .retrieve()
                .bodyToMono(Employee.class);
    }

    private Mono<Employee> fallbackForEmployeeService(Employee employee, Throwable throwable) {
        return Mono.just(new Employee()); // just send an empty object for demo
    }

    public Mono<List<Employee>> findAllEmployees() {
        return this.employeeWebClient.get()
                .uri("/employees")
                .retrieve()
                .bodyToFlux(Employee.class)
                .collectList();
    }

    public Mono<Optional<Employee>> findEmployeeById(Long id) {
        return this.employeeWebClient.get()
                .uri("/employees/{id}", id)
                .retrieve()
                .bodyToMono(Employee.class)
                .map(Optional::ofNullable);
    }

    // Methods to interact with the Department Service
    public Mono<Department> createDepartment(Department department) {
        return this.departmentWebClient.post()
                .uri("/departments")
                .body(Mono.just(department), Department.class)
                .retrieve()
                .bodyToMono(Department.class);
    }

    public Mono<List<Department>> findAllDepartments() {
        return this.departmentWebClient.get()
                .uri("/departments")
                .retrieve()
                .bodyToFlux(Department.class)
                .collectList();
    }

    public Mono<Optional<Department>> findDepartmentById(Long id) {
        return this.departmentWebClient.get()
                .uri("/departments/{id}", id)
                .retrieve()
                .bodyToMono(Department.class)
                .map(Optional::ofNullable);
    }
}
