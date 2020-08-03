package com.example.demoapp.ProductPicker;

import android.text.style.TtsSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoapp.R;
import com.example.demoapp.model.Product;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {
    private List<Product> productList;

    public ProductListAdapter (List<Product> list){
        this.productList = list;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_default_cell,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        if (product.getImageUrl().isEmpty()){
            holder.productImageView.setImageResource(R.drawable.ic_cross_main_black);
        } else {
            //ToDo (replace) look by id in cache or get picture Async and show progressbar (store in cache with product id)
            holder.productImageView.setImageResource(R.drawable.ic_cross_main_black);
        }
        holder.productName.setText(product.getName());
        holder.productDescription.setText(product.getDescription());
        //ToDo make a MoneyFormatter class (singleton)
        String priceString = "$ " + product.getPrice();
        holder.productPrice.setText(priceString);


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductViewHolder  extends  RecyclerView.ViewHolder{
        ImageView productImageView;
        TextView productName;
        TextView productDescription;
        TextView productPrice;

        public ProductViewHolder(@NonNull View v) {
            super(v);
            productImageView = v.findViewById(R.id.product_image_view);
            productName = v.findViewById(R.id.product_name_label);
            productDescription = v.findViewById(R.id.product_description_label);
            productPrice = v.findViewById(R.id.product_price_label);


        }
    }
}
