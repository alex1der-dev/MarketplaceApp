package com.gson.controller;

import com.gson.repository.ProductRepository;
import com.gson.repository.gson.GsonProductRepositoryImpl;
import com.gson.model.Product;

import java.util.List;

public class ProductController {

    private final ProductRepository productRepository = new GsonProductRepositoryImpl();

    public List<Product> getAllProducts(){
        return productRepository.getAll();
    }

    public Product getProductById(Long id){
        return productRepository.getById(id);
    }

    public Product updateProduct(Long id, String name, Double price){
        Product productUpdated = new Product();
        productUpdated.setId(id);
        productUpdated.setName(name);
        productUpdated.setPrice(price);

        return productRepository.update(productUpdated);
    }

    public Product saveProduct(String name, Double price) {
        Product productNew = new Product();
        productNew.setName(name);
        productNew.setPrice(price);

        return productRepository.save(productNew);
    }

    public void removeProduct(Long id) {
        productRepository.deleteById(id);
    }
}
