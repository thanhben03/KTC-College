package com.example.anotation.service;

import com.example.anotation.repo.ProductRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ProductService() {
        System.out.println("Initial Product Service");
    }

    @PostConstruct
    public void init() {
        System.out.println("Start Product Init Method");
    }

    public void print(String message) {
        System.out.println("Model Mapperrrrrrrrrrr: ");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Destroy Product Service");
    }
}
