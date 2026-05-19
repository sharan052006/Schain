package com.example.demo.repository;

import com.example.demo.entity.order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<order, Long> {
}
