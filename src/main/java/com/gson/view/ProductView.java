package com.gson.view;

import com.gson.controller.ProductController;
import com.gson.controller.UserController;
import com.gson.model.Product;

public class ProductView {

    private final ProductController productController = new ProductController();
    private final ConsoleReader consoleReader = new ConsoleReader();
    private final UserController userController = new UserController();

    public void addNewProduct() {
        System.out.println("Enter new product name: ");
        String name = consoleReader.readTextValue();
        System.out.println("Enter new product price: ");
        Double price = consoleReader.readDecimalValue();
        Product productAdded  = productController.saveProduct(name,price);
        System.out.println(productAdded);
        System.out.println("Added successfully.");
    }

    public void displayAllProducts() {
        for(Product p : productController.getAllProducts()) {
            System.out.println(p);
        }
    }

    public void deleteProduct(){
        System.out.println("Enter product ID to delete: ");
        System.out.println("Existing products: ");
        displayAllProducts();
        Long id = consoleReader.readNumericValue();
        productController.removeProduct(id);
        userController.deleteProductById(id);
        System.out.println("Product with ID" + id + " successfully deleted");
    }
}
