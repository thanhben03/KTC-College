package com.example.anotation.repo;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Repository
@Lazy
public class ProductRepository {
    public ProductRepository() {
        System.out.println("Initial Product Repository");
    }
}
