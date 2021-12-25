package com.example.demo.service;

import com.example.demo.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImplementation implements ProductService {
    ArrayList<Product> products = new ArrayList<Product>();

    public ProductServiceImplementation(ArrayList<Product> products) {
        this.products = products;
        products.add(new Product(1L, "Lexus", "3,500,000"));
        products.add(new Product(2L, "Tecno", "60,000"));
        products.add(new Product(3L, "Lamborghini", "70,500,000"));
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public Product getProduct(Long id) {
        Product product;
        for (int i = 0; true; i++){
            if (products.get(i).getProductID().equals(id)){
                product = products.get(i);
                return product;
            }
        }
    }

    @Override
    public void createProduct(Product product) {
        products.add(product);
    }

    @Override
    public Product updateProduct(Product product) {
        getProduct(product.getProductID()).setProductName(product.getProductName());
        getProduct(product.getProductID()).setProductPrice(product.getProductPrice());
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        products.remove(getProduct(id));
    }
}
