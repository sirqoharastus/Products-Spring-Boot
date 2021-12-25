package com.example.demo.service;

import com.example.demo.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();
    Product getProduct(Long id);
    void createProduct(Product product);
    Product updateProduct(Product product);
    void deleteProduct(Long id);
}
