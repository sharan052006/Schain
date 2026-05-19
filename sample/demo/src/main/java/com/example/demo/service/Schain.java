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

    public Schain(OrderRepository orderRepository, ProductRepository productRepository, StockRepository stockRepository, WarehouseRepository warehouseRepository, WarehouseOrderRepository warehouseOrderRepository, CustomerRepository customerRepository, SupplierRepository supplierRepository) {
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
    public product registerProduct(product product) {
        return productRepository.save(product);
    }
    public Supplier registerSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }
    public Stock registerStock(Stock stock) {
        return stockRepository.save(stock);
    }
    public warehouse registerWarehouse(warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }
    public order registerOrder(order order) {
        return orderRepository.save(order);
    }
    public Stock updateStockAvailability(Long stockId, Long available) {
        Stock stock = stockRepository.findById(stockId).orElseThrow(() -> new RuntimeException("Stock not found"));
        stock.setAvailable(available);
        stockRepository.save(stock);
        if (stock.getAvailable() != null && stock.getAvailable() < 0) {
            WarehouseOrder warehouseOrder = new WarehouseOrder();
            warehouseOrderRepository.save(warehouseOrder);
        }
        return stock;
    }

    public order placeOrder(Long customerId, Long productId, Long qty) {
        Customer c = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("customer not found"));
        product p = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("product not found"));
        Stock s = stockRepository.findAll().stream()
                .filter(x -> x.getProduct() != null && p.getPid().equals(x.getProduct().getPid()))
                .findFirst().orElseThrow(() -> new RuntimeException("stock not found"));

        long avail = s.getAvailable() == null ? 0L : s.getAvailable();
        long remaining = avail - (qty == null ? 0L : qty);
        if (remaining < 0) remaining = 0;
        s.setAvailable(remaining);
        stockRepository.save(s);

        order o = new order(); o.setCustomer(c); o = orderRepository.save(o);
        o.setStock(List.of(s)); orderRepository.save(o);

        if (avail < (qty == null ? 0L : qty)) warehouseOrderRepository.save(new WarehouseOrder());
        return o;
    }

}