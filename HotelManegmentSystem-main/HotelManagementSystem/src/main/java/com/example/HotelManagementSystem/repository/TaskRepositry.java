package com.example.HotelManagementSystem.repository;

import com.example.HotelManagementSystem.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepositry extends JpaRepository<Task, Integer> {
}
