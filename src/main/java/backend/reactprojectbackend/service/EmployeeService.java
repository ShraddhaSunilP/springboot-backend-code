package backend.reactprojectbackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import backend.reactprojectbackend.entities.Employee;

public interface EmployeeService {

	Employee AddEmployee(Employee employee);

	List<Employee> getAllEmployee();

	Optional<Employee> getEmployeeId(int id);

	Employee updateEmployee(int id, Employee employee);

	boolean deleteEmployee(int id);

	public Employee findByEmailAndPassword(String email, String password);

	

}
