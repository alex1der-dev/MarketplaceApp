package com.gson.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gson.repository.UserRepository;
import com.gson.model.User;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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
        return (users.size() == 0 ? 1L : users.get(users.size()-1).getId()+1L);
    }

    public User getById(Long id) {
        for (User u : getUsersInternal()) {
            if (u.getId().equals(id)) {
                return u;
            }
        }
        return null;
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
        User updatedUser = null;
        for (User u : existingUsers) {
            if (u.getId().equals(user.getId())) {
                u.setFirstName(user.getFirstName());
                u.setLastName(user.getLastName());
                u.setMoneyAmount(user.getMoneyAmount());
                u.setProducts(user.getProducts());
                updatedUser = u;
                break;
            }
        }
        writeUsersInternal(existingUsers);

        return updatedUser;
    }

    @Override
    public void deleteById(Long aLong) {
        List<User> existingUsers = getUsersInternal();
        User userToDelete = null;
        for (User u : existingUsers) {
            if (u.getId().equals(aLong)) {
                userToDelete = u;
            }
        }
        if (userToDelete != null) {
            existingUsers.remove(userToDelete);
        }
        writeUsersInternal(existingUsers);
    }
}
