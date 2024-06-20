package com.example.HotelManagementSystem.repository;

import com.example.HotelManagementSystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepositry  extends JpaRepository<Employee,Integer> {
     Employee findEmployeeByName(String username);
}
