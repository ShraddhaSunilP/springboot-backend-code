package backend.reactprojectbackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import backend.reactprojectbackend.entities.Employee;
import backend.reactprojectbackend.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	 @Autowired
	 EmployeeRepository employeeRepository;

	@Override
	public Employee AddEmployee(Employee employee) { 
		employeeRepository.save(employee);
		return employee;
	}

	@Override
	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}

	@Override
	public Optional<Employee> getEmployeeId(int id) {
		return employeeRepository.findById(id);
	}

	@Override
	public Employee updateEmployee(int id, Employee employee) {
		employeeRepository.save(employee);
		return employee;
	}

	@Override
	public boolean deleteEmployee(int id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if(employee.isPresent()) {
			employeeRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	public Employee findByEmailAndPassword(String email, String password) {
        return employeeRepository.findByEmailAndPassword(email, password);
    }
}
