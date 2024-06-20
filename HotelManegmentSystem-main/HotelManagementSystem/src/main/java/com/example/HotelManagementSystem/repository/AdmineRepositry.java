package com.example.HotelManagementSystem.repository;

import com.example.HotelManagementSystem.entity.Admine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdmineRepositry  extends JpaRepository<Admine, Integer> {
//    boolean  existsByUserEmail(String email);
    Optional<Admine> findByUserEmail(String email);
//    List<Admine> findByEmail(String email);
//    Admine findByUsername(String username);

}
