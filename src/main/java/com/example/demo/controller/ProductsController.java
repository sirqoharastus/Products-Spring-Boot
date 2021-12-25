package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductsController {
    @Autowired
    ProductServiceImplementation productServiceImplementation;

    @GetMapping("")
    List<Product> getProducts() {
        return productServiceImplementation.getProducts();
    }

    @GetMapping("/{id}")
    Product getProduct(@PathVariable("id") Long id) {
        return productServiceImplementation.getProduct(id);
    }

    @GetMapping("/getproducts")
    ResponseEntity<List<Product>> hanndle2() {
        CacheControl cacheControl = CacheControl.noCache();
        return ResponseEntity.ok().cacheControl(cacheControl).body(productServiceImplementation.getProducts());
    }

    @PatchMapping("{id}")
    public @ResponseBody void saveManager(@PathVariable("id") Long id, @RequestBody Map<Object, Object> fields) {
        Product product = productServiceImplementation.getProduct(id);
        fields.forEach((k, v) -> {
            Field field = ReflectionUtils.findField(Product.class, k + "");
            assert field != null;
            field.setAccessible(true);
            ReflectionUtils.setField(field, product, v);
        });
        productServiceImplementation.updateProduct(product);
    }

    @PostMapping("")
    String createProduct(@RequestBody Product product) {
        productServiceImplementation.createProduct(product);
        return "Product successfully added";
    }

    @PutMapping("")
    Product updateProduct(@RequestBody Product product) {
        return productServiceImplementation.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    Map<String, Object> deleteProduct(@PathVariable("id") Long id) {
        productServiceImplementation.deleteProduct(id);
        Map<String, Object> deleteResponse = Map.of("status", "Successful");
        return deleteResponse;
    }
}
