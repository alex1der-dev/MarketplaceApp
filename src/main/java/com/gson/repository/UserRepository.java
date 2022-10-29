package com.gson.repository;

import com.gson.model.Product;
import com.gson.model.User;

import java.util.List;

public interface UserRepository extends GenericRepository<User,Long>{

    void deleteUserProductById(Product p);

    List<User> getUsersByProduct(Product product);

    void buyProduct(User user, Product product);
}
