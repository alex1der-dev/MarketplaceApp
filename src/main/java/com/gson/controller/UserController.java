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
        Product productOwned = productController.getProductById(id);

        return  userRepository.getUsersByProduct(productOwned);
    }

    public void buyProduct(Long userId, Long productId) {
        User userBuyer = getUserById(userId);
        Product productToBuy = productController.getProductById(productId);
        if (userBuyer.getMoneyAmount() < productToBuy.getPrice()) {
            throw new UnsupportedOperationException();
        }
        userRepository.buyProduct(userBuyer,productToBuy);
    }

    public List<Product> getUserProducts(Long userId) {
        User userProducts = getUserById(userId);
        return userProducts.getProducts();
    }

    public void deleteProductById(Long id) {
        Product productToDelete = productController.getProductById(id);
        userRepository.deleteUserProductById(productToDelete);
    }
}
