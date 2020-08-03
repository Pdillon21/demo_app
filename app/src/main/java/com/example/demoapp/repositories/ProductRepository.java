package com.example.demoapp.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.example.demoapp.DAO.ProductDao;
import com.example.demoapp.databases.ProductDatabase;
import com.example.demoapp.model.Product;


import java.util.List;

public class ProductRepository {

    private ProductDao productDao;
    private List<Product> allProducts;

    public ProductRepository (Application application){
        ProductDatabase productDatabase = ProductDatabase.getInstance(application);
        productDao = productDatabase.productDao();
        productDao.getAllProducts();
    }

    public void insert (Product product){
        new ProductRepository.InsertProductAsyncTask(productDao).execute(product);
    }

    public void update (Product product){
        new ProductRepository.UpdateProductAsyncTask(productDao).execute(product);
    }

    public void delete (Product product){
        new ProductRepository.DeleteProductAsyncTask(productDao).execute(product);
    }

    public List<Product> getAllProducts (){
        return allProducts;
    }

    public List<Product> getProductByName (String productName){
        return productDao.getProductByName(productName);
    }

    public List<Product> getProductById (int productId){
        return productDao.getProductById(productId);
    }

    public List<Product> getUpdatedAllProducts() {
        return productDao.getAllProducts();
    }


    private static class UpdateProductAsyncTask extends AsyncTask<Product,Void,Void> {
        private ProductDao productDao;

        private UpdateProductAsyncTask(ProductDao productDao){
            this.productDao = productDao;
        }
        @Override
        protected Void doInBackground(Product... products) {
            productDao.update(products[0]);
            return null;
        }
    }

    private static class InsertProductAsyncTask extends AsyncTask<Product,Void,Void> {
        private ProductDao productDao;

        private InsertProductAsyncTask(ProductDao productDao){
            this.productDao = productDao;
        }
        @Override
        protected Void doInBackground(Product... products) {
            productDao.insert(products[0]);
            return null;
        }
    }

    private static class DeleteProductAsyncTask extends AsyncTask<Product,Void,Void> {
        private ProductDao productDao;

        private DeleteProductAsyncTask(ProductDao productDao){
            this.productDao = productDao;
        }
        @Override
        protected Void doInBackground(Product... products) {
            productDao.delete(products[0]);
            return null;
        }
    }







}
