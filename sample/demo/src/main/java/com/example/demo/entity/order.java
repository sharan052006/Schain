package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Oid;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private List<Stock> stock;

}
