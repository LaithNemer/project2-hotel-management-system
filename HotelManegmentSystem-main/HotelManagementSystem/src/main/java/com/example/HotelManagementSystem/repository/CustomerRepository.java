package com.example.HotelManagementSystem.repository;

import com.example.HotelManagementSystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
//    boolean  existsByEmail(String email);
//    List<Customer> findByEmail(String email);
    Optional<Customer> findByUserEmail(String email);

//    boolean existsByEmail(String email);
//    Customer findByEmail(String email);
//    Customer findByUsername(String username);
//    List<Customer> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);
}