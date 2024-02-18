package backend.reactprojectbackend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.reactprojectbackend.entities.Employee;
import backend.reactprojectbackend.service.EmployeeService;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	//Add Employee
	@PostMapping("/addEmployee")
	public Employee AddEmployee(@RequestBody Employee employee) {
		return employeeService.AddEmployee(employee);
	} 
	
	//Get All Employee
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllEmployee(){
		List<Employee> employee = employeeService.getAllEmployee();
		if(!employee.isEmpty()) {
			return ResponseEntity.ok(employee);
		} else {
			String errMsg = "No Records Found";
			return new ResponseEntity<>(errMsg, HttpStatus.NOT_FOUND);
		}
	}
	
	//get employee by id
	@GetMapping("/{id}")
	public ResponseEntity<?> getEmployeeId(@PathVariable int id){
		Optional<Employee> emp = employeeService.getEmployeeId(id);
		if(emp.isPresent()) {
			return ResponseEntity.ok(emp.get());
		} else {
			String errMsg = "Employee with ID " + id + "is not Found";
			return new ResponseEntity<>(errMsg, HttpStatus.NOT_FOUND);
		}
	}
	
	//update employee by id
	@PutMapping("/{id}")
	public Employee updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
		return employeeService.updateEmployee(id, employee);
	}
	
	//delete By id
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable int id){
		boolean isdeleted = employeeService.deleteEmployee(id);
		if(isdeleted) {
			String successMsg = "Employee with ID "+id+" is deleted";
			return ResponseEntity.ok(successMsg);
		} else {
			String errMsg = "Employee with ID "+id+" is not found";
			return new ResponseEntity<>(errMsg, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody Employee employee) {
        String email = employee.getEmail();
        String password = employee.getPassword();

        // Authenticate user using the EmployeeService
        Employee emp = employeeService.findByEmailAndPassword(email, password);

        if (emp != null) {
            // User authenticated successfully
            return ResponseEntity.ok("Authentication successful");
        } else {
            // Authentication failed
            return ResponseEntity.badRequest().body("Authentication failed");
        }
    }
}
