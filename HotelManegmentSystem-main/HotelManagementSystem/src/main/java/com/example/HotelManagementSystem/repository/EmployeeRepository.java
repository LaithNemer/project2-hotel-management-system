package com.example.HotelManagementSystem.repository;

import com.example.HotelManagementSystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByAdmineId(int admineId);
    boolean existsByUsername(String username);
    Employee findByUsername(String username);

}