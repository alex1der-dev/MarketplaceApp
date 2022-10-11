package com.gson.repository.gson;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.gson.repository.ProductRepository;
import com.gson.model.Product;


public class GsonProductRepositoryImpl implements ProductRepository {

    private static final String PRODUCTS_FILE_PATH = "src/main/resources/products.json";
    private static final Gson GSON = new Gson();

    private List<Product> getProductsInternal() {
        try {
            String json = Files.readString(Paths.get(PRODUCTS_FILE_PATH));
            Type targetClassType = new TypeToken<ArrayList<Product>>() {
            }.getType();
            return GSON.fromJson(json, targetClassType);
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    private void writeProductsInternal(List<Product> products) {
        String json = GSON.toJson(products);
        try {
            Files.writeString(Paths.get(PRODUCTS_FILE_PATH), json);
        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }

    private Long generateId(List<Product> products) {
        return (products.size() == 0 ? 1L : products.get(products.size()-1).getId() + 1L);
    }

    public Product getById(Long id) {
        for (Product p : getProductsInternal()) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Product> getAll() {
        return getProductsInternal();
    }

    @Override
    public Product save(Product product) {
        List<Product> existingProducts = getProductsInternal();
        product.setId(generateId(existingProducts));
        existingProducts.add(product);
        writeProductsInternal(existingProducts);

        return product;
    }

    @Override
    public Product update(Product product) {
        List<Product> existingProducts = getProductsInternal();
        Product updatedProduct = null;
        for (Product p : existingProducts) {
            if (p.getId().equals(product.getId())) {
                p.setName(product.getName());
                p.setPrice(product.getPrice());
                updatedProduct = p;
            }
        }
        writeProductsInternal(existingProducts);

        return updatedProduct;
    }

    @Override
    public void deleteById(Long aLong) {
        List<Product> existingProducts = getProductsInternal();
        Product productToDelete = null;
        for (Product p : existingProducts) {
            if (p.getId().equals(aLong)) {
                productToDelete = p;
            }
        }
        if (productToDelete != null) {
            existingProducts.remove(productToDelete);
        }
        writeProductsInternal(existingProducts);
    }
}
