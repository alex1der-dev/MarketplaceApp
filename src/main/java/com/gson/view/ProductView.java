package com.gson.view;

import com.gson.controller.ProductController;
import com.gson.controller.UserController;
import com.gson.model.Product;

import java.util.List;

public class ProductView {

    private final ProductController productController = new ProductController();
    private final ConsoleReader consoleReader = new ConsoleReader();
    private final UserController userController = new UserController();

    public void addNewProduct() {
        System.out.println("Enter new product name: ");
        String name = consoleReader.readTextValue();
        System.out.println("Enter new product price: ");
        Double price = consoleReader.readDecimalValue();
        Product productAdded = productController.saveProduct(name, price);
        System.out.println(productAdded);
        System.out.println("Added successfully.");
    }

    public void displayAllProducts() {
        List<Product> products = productController.getAllProducts();
        if (products == null || products.isEmpty()) {
            System.out.println("No products found.");
            return;
        }
        System.out.println("Existing products: ");
        products.forEach(System.out::println);
    }

    public void deleteProduct() {
        try {
            displayAllProducts();
        } catch (IllegalArgumentException e) {
            System.out.println("No existing products.");
            return;
        }

        System.out.println("Enter product ID to delete: ");
        Long id = consoleReader.readNumericValue();
        String productDeleted = productController.getProductById(id).toString();
        productController.removeProduct(id);
        userController.deleteProductById(id);
        System.out.println(productDeleted);
        System.out.println("Successfully deleted.");
    }
}
