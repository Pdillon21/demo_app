package com.example.demoapp.SplashScreen;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.demoapp.model.Product;
import com.example.demoapp.model.User;
import com.example.demoapp.repositories.ProductRepository;
import com.example.demoapp.repositories.UserRepository;

import java.util.List;

public class SplashViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private List<User> allUsers;
    private ProductRepository productRepository;
    private List<Product> allProducts;

    public SplashViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        allUsers = userRepository.getUser("null");

        productRepository = new ProductRepository(application);
        allProducts = productRepository.getProductByName("null");

    }

    public List<User> getAllUsers() {
        return allUsers;
    }

    public User getUser (String userName){
        List<User> aUserList = userRepository.getUser(userName);
        User aUSer = null;
        if (!aUserList.isEmpty()){
            aUSer = aUserList.get(0);
        }
        return aUSer;
    }

    public Product getProduct (String productName){
        List<Product> aProductList = productRepository.getProductByName(productName);
        Product aProduct = null;
        if (!aProductList.isEmpty()){
            aProduct = aProductList.get(0);
        }
        return aProduct;
    }

    public List<Product> getAllProducts() {
        return allProducts;
    }
}
