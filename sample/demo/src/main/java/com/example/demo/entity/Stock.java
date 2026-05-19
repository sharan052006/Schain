package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Sid;
    private Long capacity;
    private Long available;

    @ManyToOne
    @JoinColumn(name = "Pid")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "Wid")
    private Warehouse warehouse;

    @OneToOne
    @JoinColumn(name="SId")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "Oid")
    private Order order;

}
