package com.example.demoapp.ProductPicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.demoapp.R;
import com.example.demoapp.model.Product;

import java.util.List;

public class ProductPickerActivity extends AppCompatActivity {

    private Context context;
    private ProductPickerViewModel viewModel;
    private List<Product> allProducts;
    private RecyclerView productListView;
    private ProductListAdapter listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_product_picker);
        viewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ProductPickerViewModel.class);
        allProducts = viewModel.getAllProducts();
        findViews();
        if (allProducts==null || allProducts.isEmpty()){
            Toast.makeText(context, "No products to display", Toast.LENGTH_SHORT).show();
        } else {
            setUpListView();
        }
    }

    private void findViews() {
        productListView = findViewById(R.id.product_list_view);
        productListView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        productListView.setHasFixedSize(true);
    }

    public static void navigate (Activity activity){
        Intent intent = new Intent(activity, ProductPickerActivity.class);
        ActivityCompat.startActivityForResult(activity,intent,0,null);
    }

    private void setUpListView() {
        listAdapter = new ProductListAdapter(allProducts);
        productListView.setAdapter(listAdapter);
    }
}
