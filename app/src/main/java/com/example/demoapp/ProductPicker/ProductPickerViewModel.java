package com.example.demoapp.ProductPicker;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.demoapp.model.Product;
import com.example.demoapp.model.User;
import com.example.demoapp.repositories.ProductRepository;
import com.example.demoapp.repositories.UserRepository;

import java.util.List;

public class ProductPickerViewModel extends AndroidViewModel {

    private User currentUser;
    private ProductRepository productRepository;
    private List<Product> allProducts;

    public ProductPickerViewModel(@NonNull Application application) {
        super(application);
        productRepository = new ProductRepository(application);
        allProducts = productRepository.getUpdatedAllProducts();
    }

    public List<Product> getAllProducts() {
        return allProducts;
    }

    public List<Product> getProductByName(String productName){
        return productRepository.getProductByName(productName);
    }

}
