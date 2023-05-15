package xyz.navinda.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.navinda.employee.entity.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> { }