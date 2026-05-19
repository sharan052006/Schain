package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Pid;
    private String Pname;
    private Long price;
    
    public Long getPid() {
        return Pid;
    }

    public String getPname() {
        return Pname;
    }

    public Long getPrice() {
        return price;
    }

    public void setPname(String Pname) {
        this.Pname = Pname;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

}
