package com.gson.view;

import com.gson.controller.ProductController;
import com.gson.controller.UserController;
import com.gson.model.Product;
import com.gson.model.User;

import java.util.List;

public class UserView {

    private final UserController userController = new UserController();
    private final ProductController productController = new ProductController();
    private final ConsoleReader consoleReader = new ConsoleReader();
    private final ProductView productView = new ProductView();

    public void addUser() {
        System.out.println("Enter new user first name: ");
        String userFirstName = consoleReader.readUserName();
        System.out.println("Enter new user last name: ");
        String userLastName = consoleReader.readUserName();
        System.out.println("Enter user money amount: ");
        Double moneyAmount = consoleReader.readDecimalValue();
        User userAdded = userController.saveUser(userFirstName, userLastName, moneyAmount);
        System.out.println(userAdded);
        System.out.println("Successfully registered.");
    }

    public void displayAllUsers() {
        List<User> users = userController.getAllUsers();
        for (User u : users) {
            System.out.println(u);
        }
    }

    public void buyProduct() {

        System.out.println("Enter user ID: ");
        System.out.println("Registered users: ");
        displayAllUsers();
        Long userId = consoleReader.readNumericValue();
        System.out.println("Enter product ID: ");
        System.out.println("Existing products: ");
        productView.displayAllProducts();
        Long productId = consoleReader.readNumericValue();
        try {
            userController.buyProduct(userId, productId);
        } catch (IllegalArgumentException e) {
            System.out.println("User has not enough money " + userController.getUserById(userId).getMoneyAmount()
                    + " to buy product " + productController.getProductById(productId).getName()
                    + " with price " + productController.getProductById(productId).getPrice());
        }
        System.out.println(userController.getUserById(userId) + " successfully purchased " + productController.getProductById(productId));
    }

    public void displayUserProducts() {
        System.out.println("Enter user ID: ");
        System.out.println("Registered users: ");
        displayAllUsers();
        Long userId = consoleReader.readNumericValue();
        List<Product> userProducts = userController.getUserProducts(userId);
        for (Product p : userProducts) {
            System.out.println(p);
        }
    }

    public void displayUsersHaveProduct() {
        System.out.println("Enter product ID users bought: ");
        System.out.println("Existing products: ");
        productView.displayAllProducts();
        Long productId = consoleReader.readNumericValue();
        List<User> userHaveProducts = userController.getUserByProductId(productId);
        for (User u : userHaveProducts) {
            System.out.println(u);
        }
    }

    public void deleteUser() {
        System.out.println("Enter user ID to delete: ");
        System.out.println("Registered users: ");
        displayAllUsers();
        Long userId = consoleReader.readNumericValue();
        userController.removeUser(userId);
    }
}
