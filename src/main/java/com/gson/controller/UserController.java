package com.gson.controller;

import com.gson.model.User;
import com.gson.model.Product;
import com.gson.repository.UserRepository;
import com.gson.repository.gson.GsonUserRepositoryImpl;


import java.util.*;

public class UserController {

    private final UserRepository userRepository = new GsonUserRepositoryImpl();
    private final ProductController productController = new ProductController();

    public List<User> getAllUsers() {
        return userRepository.getAll();
    }

    public User getUserById(Long id) {
        return userRepository.getById(id);
    }

    public User updateUser(Long id, String firstName, String lastName, Double moneyAmount, List<Product> products) {
        User userUpdated = new User();
        userUpdated.setId(id);
        userUpdated.setFirstName(firstName);
        userUpdated.setLastName(lastName);
        userUpdated.setMoneyAmount(moneyAmount);
        userUpdated.setProducts(products);

        return userRepository.update(userUpdated);
    }

    public User saveUser(String firstName, String lastName, Double moneyAmount) {
        User userNew = new User();
        userNew.setFirstName(firstName);
        userNew.setLastName(lastName);
        userNew.setMoneyAmount(moneyAmount);

        return userRepository.save(userNew);
    }

    public void removeUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> getUserByProductId(Long id) {
        List<User> existingUsers = getAllUsers();
        List<User> usersHaveProduct = new ArrayList<>();
        for (User u : existingUsers) {
            for (Product p : u.getProducts()) {
                if (p.getId().equals(id)) {
                    usersHaveProduct.add(u);
                }
            }
        }
        return usersHaveProduct;
    }

    public void buyProduct(Long userId, Long productId) {
        User userBuyer = getUserById(userId);
        Product productToBuy = productController.getProductById(productId);
        if (userBuyer.getMoneyAmount() < productToBuy.getPrice()) {
            throw new IllegalArgumentException();
        }
        Double moneyAmount = userBuyer.getMoneyAmount() - productToBuy.getPrice();
        userBuyer.setMoneyAmount(moneyAmount);
        List<Product> userBuyerProducts = userBuyer.getProducts();
        if (userBuyerProducts == null) {
            userBuyerProducts = new ArrayList<>();
        }
        userBuyerProducts.add(productToBuy);

        updateUser(userId, userBuyer.getFirstName(), userBuyer.getLastName(), userBuyer.getMoneyAmount(), userBuyerProducts);
    }

    public List<Product> getUserProducts(Long userId) {
        User userProducts = getUserById(userId);
        return userProducts.getProducts();
    }

    public void deleteProductById(Long id) {
        List<User> existingUsers = getAllUsers();
        Product productToDelete = productController.getProductById(id);
        for (User u : existingUsers) {
            List<Product> userProductsModified = u.getProducts();
            if (userProductsModified != null) {
                userProductsModified.remove(productToDelete);
                updateUser(u.getId(), u.getFirstName(), u.getLastName(), u.getMoneyAmount(), userProductsModified);
            }
        }
    }
}
