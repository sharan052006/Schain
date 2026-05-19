package com.example.demo.controller;

import com.example.demo.entity.*;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import java.util.Scanner;

@Component
public class Schain implements CommandLineRunner {
    private final com.example.demo.service.Schain schainService;

    public Schain(com.example.demo.service.Schain schainService) { this.schainService = schainService; }

    @Override
    public void run(String... args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("Supply Chain CLI started. Enter 0 to exit.");
        while (true) {
            System.out.println("1:Register Customer  2:Register Product  3:Register Stock  4:Place Order  0:Exit");
            int choice = input.nextInt();
            if (choice == 0) break;
            try {
                switch (choice) {
                    case 1 -> {
                        Customer c = new Customer();
                        System.out.print("name: "); c.setCname(input.next());
                        System.out.print("email: "); c.setCemail(input.next());
                        schainService.registerCustomer(c);
                        System.out.println("customer saved");
                    }
                    case 2 -> {
                        product p = new product();
                        System.out.print("pname: "); p.setPname(input.next());
                        System.out.print("price: "); p.setPrice(input.nextLong());
                        schainService.registerProduct(p);
                        System.out.println("product saved");
                    }
                    case 3 -> {
                        Stock s = new Stock();
                        System.out.print("available: "); s.setAvailable(input.nextLong());
                        System.out.print("capacity: "); s.setCapacity(input.nextLong());
                        schainService.registerStock(s);
                        System.out.println("stock saved");
                    }
                    case 4 -> {
                        System.out.print("customerId: "); Long cid = input.nextLong();
                        System.out.print("productId: "); Long pid = input.nextLong();
                        System.out.print("qty: "); Long qty = input.nextLong();
                        schainService.placeOrder(cid, pid, qty);
                        System.out.println("order placed");
                    }
                    default -> System.out.println("invalid");
                }
            } catch (Exception e) {
                System.out.println("error: " + e.getMessage());
            }
        }
        input.close();
    }
}
