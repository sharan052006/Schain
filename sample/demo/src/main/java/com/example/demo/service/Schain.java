package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;

import java.util.List;

@Service
public class Schain {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final StockRepository stockRepository;
    private final WarehouseRepository warehouseRepository;
    private final WarehouseOrderRepository warehouseOrderRepository;
    private final CustomerRepository customerRepository;
    private final SupplierRepository supplierRepository;

    public Schain(OrderRepository orderRepository,
                  ProductRepository productRepository,
                  StockRepository stockRepository,
                  WarehouseRepository warehouseRepository,
                  WarehouseOrderRepository warehouseOrderRepository,
                  CustomerRepository customerRepository,
                  SupplierRepository supplierRepository) {

        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.stockRepository = stockRepository;
        this.warehouseRepository = warehouseRepository;
        this.warehouseOrderRepository = warehouseOrderRepository;
        this.customerRepository = customerRepository;
        this.supplierRepository = supplierRepository;
    }

    
    public Customer registerCustomer(Customer customer) {

        return customerRepository.save(customer);
    }

    
    public Product registerProduct(Product product) {

        return productRepository.save(product);
    }

    
    public Supplier registerSupplier(Supplier supplier) {

        return supplierRepository.save(supplier);
    }

    public Stock registerStock(Long productId, Stock stock) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        stock.setProduct(product);

        return stockRepository.save(stock);
    }

    
    public Warehouse registerWarehouse(Warehouse warehouse) {

        return warehouseRepository.save(warehouse);
    }

    
    public Order placeOrder(Long customerId, Long productId, Long qty) {

        
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

    
        Stock stock = stockRepository.findAll()
                .stream()
                .filter(s -> s.getProduct() != null
                        && s.getProduct().getPid().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Stock not found"));

        Long available = stock.getAvailable();

       
        if (available < qty) {

            WarehouseOrder warehouseOrder = new WarehouseOrder();

            warehouseOrder.setProduct(product);

            warehouseOrder.setQuantity(qty - available);

            warehouseOrderRepository.save(warehouseOrder);

            throw new RuntimeException("Insufficient stock. Warehouse order created");
        }

        
        stock.setAvailable(available - qty);

        stockRepository.save(stock);

       
        Order order = new Order();

        order.setCustomer(customer);

        order.setStock(List.of(stock));

        orderRepository.save(order);

        
        if (stock.getAvailable() == 0) {

            WarehouseOrder warehouseOrder = new WarehouseOrder();

            warehouseOrder.setProduct(product);

            warehouseOrder.setQuantity(50L);

            warehouseOrderRepository.save(warehouseOrder);
        }

        return order;
    }
}