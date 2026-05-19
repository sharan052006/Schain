package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Oid;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private List<Stock> stock;

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setStock(List<Stock> stock) {
        this.stock = stock;
    }

}
