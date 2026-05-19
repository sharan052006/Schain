package com.example.demo.repository;

import com.example.demo.entity.WarehouseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseOrderRepository extends JpaRepository<WarehouseOrder, Long> {
}
