package com.example.demo.controller;

import com.example.demo.entity.*;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import java.util.Scanner;

@Component
public class SchainController implements CommandLineRunner {

    private final com.example.demo.service.Schain schainService;

    public SchainController(com.example.demo.service.Schain schainService) {
        this.schainService = schainService;
    }

    @Override
    public void run(String... args) throws Exception {

        Scanner input = new Scanner(System.in);

        System.out.println("Supply Chain CLI Started");

        while (true) {

            System.out.println("""
                    
                    1. Register Customer
                    2. Register Product
                    3. Register Stock
                    4. Place Order
                    0. Exit
                    
                    """);

            System.out.print("Enter choice: ");

            int choice = input.nextInt();

            if (choice == 0) {
                System.out.println("Exited");
                break;
            }

            try {

                switch (choice) {

                    case 1 -> {

                        Customer customer = new Customer();

                        System.out.print("Enter customer name: ");
                        customer.setCname(input.next());

                        System.out.print("Enter customer email: ");
                        customer.setCemail(input.next());

                        schainService.registerCustomer(customer);

                        System.out.println("Customer Registered");
                    }

                    case 2 -> {

                        Product product = new Product();

                        System.out.print("Enter product name: ");
                        product.setPname(input.next());

                        System.out.print("Enter product price: ");
                        product.setPrice(input.nextLong());

                        schainService.registerProduct(product);

                        System.out.println("Product Registered");
                    }

                    case 3 -> {

                        System.out.print("Enter product id: ");
                        Long productId = input.nextLong();

                        Stock stock = new Stock();

                        System.out.print("Enter available stock: ");
                        stock.setAvailable(input.nextLong());

                        System.out.print("Enter stock capacity: ");
                        stock.setCapacity(input.nextLong());

                        schainService.registerStock(productId, stock);

                        System.out.println("Stock Registered");
                    }

                    case 4 -> {

                        System.out.print("Enter customer id: ");
                        Long customerId = input.nextLong();

                        System.out.print("Enter product id: ");
                        Long productId = input.nextLong();

                        System.out.print("Enter quantity: ");
                        Long qty = input.nextLong();

                        schainService.placeOrder(customerId, productId, qty);

                        System.out.println("Order Placed");
                    }

                    default -> System.out.println("Invalid Choice");
                }

            } catch (Exception e) {

                System.out.println("Error : " + e.getMessage());
            }
        }

        input.close();
    }
}