package com.gson.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gson.model.Product;
import com.gson.repository.UserRepository;
import com.gson.model.User;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class GsonUserRepositoryImpl implements UserRepository {

    private static final String USERS_FILE_PATH = "src/main/resources/users.json";
    private static final Gson GSON = new Gson();

    private List<User> getUsersInternal() {
        try {
            String json = Files.readString(Paths.get(USERS_FILE_PATH));
            Type targetClassType = new TypeToken<ArrayList<User>>() {
            }.getType();
            return GSON.fromJson(json, targetClassType);
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    private void writeUsersInternal(List<User> users) {
        String json = GSON.toJson(users);
        try {
            Files.writeString(Paths.get(USERS_FILE_PATH), json);
        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }

    private Long generateId(List<User> users) {
        User userMaxId = users.stream().max(Comparator.comparing(User::getId)).orElse(null);
        return Objects.nonNull(userMaxId) ? userMaxId.getId() + 1L : 1L;
    }

    public User getById(Long id) {

        return getUsersInternal().stream().filter(u -> u.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<User> getAll() {
        return getUsersInternal();
    }

    @Override
    public User save(User user) {
        List<User> existingUsers = getUsersInternal();
        user.setId(generateId(existingUsers));
        existingUsers.add(user);
        writeUsersInternal(existingUsers);

        return user;
    }

    @Override
    public User update(User user) {
        List<User> existingUsers = getUsersInternal();

        existingUsers.forEach(u -> {
            if (u.getId().equals(user.getId())) {
                u.setFirstName(user.getFirstName());
                u.setLastName(user.getLastName());
                u.setMoneyAmount(user.getMoneyAmount());
                u.setProducts(user.getProducts());
            }
        });
        writeUsersInternal(existingUsers);

        return user;
    }

    @Override
    public void deleteById(Long id) {
        List<User> existingUsers = getUsersInternal();
        existingUsers.removeIf(u -> u.getId().equals(id));
        writeUsersInternal(existingUsers);
    }

    @Override
    public void deleteUserProductById(Product productToDelete) {
        List<User> existingUsers = getUsersInternal();

        existingUsers.forEach(u -> {
            List<Product> userProductsUpdated = u.getProducts();
            if (userProductsUpdated != null) {
                userProductsUpdated.remove(productToDelete);
                update(u);
            }
        });
        writeUsersInternal(existingUsers);
    }

    @Override
    public List<User> getUsersByProduct(Product product) {
        List<User> existingUsers = getUsersInternal();

       return existingUsers.stream()
                .filter(u -> u.getProducts().contains(product))
                .collect(Collectors.toList());
    }

    @Override
    public void buyProduct(User user, Product product) {
        Double moneyAmount = user.getMoneyAmount() - product.getPrice();
        user.setMoneyAmount(moneyAmount);
        List<Product> userProducts = user.getProducts();
        userProducts.add(product);
        update(user);
    }
}