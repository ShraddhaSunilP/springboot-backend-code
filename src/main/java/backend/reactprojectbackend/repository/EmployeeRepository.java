package backend.reactprojectbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import backend.reactprojectbackend.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	@Query(value = "SELECT * FROM employee e WHERE e.email = :email AND e.password = :password", nativeQuery = true)
    Employee findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}
