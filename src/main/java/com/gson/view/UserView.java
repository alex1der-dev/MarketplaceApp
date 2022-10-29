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
        if (users == null || users.isEmpty()) {
            System.out.println("No registered users");
            return;
        }
        System.out.println("Registered users: ");
        users.forEach(System.out::println);
    }

    public void buyProduct() {
        List<User> usersAll = userController.getAllUsers();
        if (usersAll==null||usersAll.isEmpty()) {
            System.out.println("No registered users.");
            return;
        }
        List<Product> productsAll = productController.getAllProducts();
        if (productsAll==null||productsAll.isEmpty()) {
            System.out.println("No existing products.");
            return;
        }
        displayAllUsers();
        System.out.println("Enter user ID: ");
        Long userId = consoleReader.readNumericValue();
        productView.displayAllProducts();
        System.out.println("Enter product ID: ");
        Long productId = consoleReader.readNumericValue();
        try {
            userController.buyProduct(userId, productId);
        } catch (UnsupportedOperationException e) {
            String userFullName = userController.getUserById(userId).getFirstName()
                    + userController.getUserById(userId).getLastName();
            System.out.println("User " + userFullName
                    + " has not enough money " + userController.getUserById(userId).getMoneyAmount()
                    + " to buy product " + productController.getProductById(productId).getName()
                    + " with price " + productController.getProductById(productId).getPrice());
        }
        System.out.println(userController.getUserById(userId) + " successfully purchased " + productController.getProductById(productId));
    }

    public void displayUserProducts() {
        System.out.println("Enter user ID: ");
        displayAllUsers();
        Long userId = consoleReader.readNumericValue();
        try {
            List<Product> userProducts = userController.getUserProducts(userId);
            userProducts.forEach(System.out::println);
        } catch (NullPointerException e) {
            System.out.println("User has no products");
        }
    }

    public void displayUsersHaveProduct() {
        System.out.println("Enter product ID users bought: ");
        productView.displayAllProducts();
        Long productId = consoleReader.readNumericValue();
        List<User> userHaveProducts = userController.getUserByProductId(productId);
        for (User u : userHaveProducts) {
            System.out.println(u);
        }
    }

    public void deleteUser() {
        try {
            displayAllUsers();
        } catch (IllegalArgumentException e) {
            System.out.println("No registered users");
            return;
        }

        System.out.println("Enter user ID to delete: ");
        Long userId = consoleReader.readNumericValue();
        String userToDelete = userController.getUserById(userId).toString();
        userController.removeUser(userId);
        System.out.println(userToDelete);
        System.out.println("Successfully deleted.");
    }
}
