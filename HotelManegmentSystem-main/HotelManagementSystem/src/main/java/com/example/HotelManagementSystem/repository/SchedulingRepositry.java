package com.example.HotelManagementSystem.repository;

import com.example.HotelManagementSystem.entity.Scheduling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SchedulingRepositry extends JpaRepository<Scheduling, Integer> {
}
