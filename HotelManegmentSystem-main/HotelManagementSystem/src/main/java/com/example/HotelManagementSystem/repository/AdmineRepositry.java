package com.example.HotelManagementSystem.repository;

import com.example.HotelManagementSystem.entity.Admine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmineRepositry  extends JpaRepository<Admine, Integer> {
    boolean  existsByEmail(String email);

}
