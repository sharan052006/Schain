package com.example.demo.repository;

import com.example.demo.entity.warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<warehouse, Long> {
}
