package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Sid;
    private Long capacity;
    private Long available;

    @OneToMany
    @JoinColumn(name = "Pid")
    private product product;

    @ManyToMany(mappedBy = "stock")
    private warehouse warehouse;

    @OneToOne
    @JoinColumn(name="SId")
    private Supplier supplier;

    @ManyToMany
    @JoinColumn(name = "Oid")
    private order order;

}
