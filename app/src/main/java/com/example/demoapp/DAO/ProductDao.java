package com.example.demoapp.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.demoapp.model.Product;
import com.example.demoapp.model.User;

import java.util.List;
@Dao
public interface ProductDao {
    @Insert
    void insert(Product product);

    @Update
    void update (Product product);

    @Delete
    void delete (Product product);

    @Query("SELECT * FROM product_table")
    List<Product> getAllProducts();

    @Query("SELECT * FROM product_table WHERE prouct_name = :productName")
    List<Product> getProductByName(String productName);

    @Query("SELECT * FROM product_table where prouct_id = :id")
    List<Product> getProductById (int id);
}
