package com.example.HotelManagementSystem.repository;

import com.example.HotelManagementSystem.entity.Admine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdmineRepositry  extends JpaRepository<Admine, Integer> {
    boolean  existsByEmail(String email);
    List<Admine> findByEmail(String email);


}
