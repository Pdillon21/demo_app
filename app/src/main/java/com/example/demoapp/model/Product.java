package com.example.demoapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "product_table")
public class Product implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "prouct_id")
    private int accesibleId;

    @ColumnInfo(name = "prouct_name")
    private String name;

    @ColumnInfo(name = "prouct_description")
    private String description;

    @ColumnInfo(name = "prouct_price")
    private Double price;

    @ColumnInfo(name = "prouct_imageUrl")
    private String imageUrl;

    @ColumnInfo(name = "prouct_measureType")
    private String measureType;

    @ColumnInfo(name = "prouct_type")
    private String productType;

    public Product(String name, String description, Double price, String imageUrl, String measureType, String productType) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.measureType = measureType;
        this.productType = productType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setAccesibleId(int accesibleId) {
        this.accesibleId = accesibleId;
    }

    public int getAccesibleId() {
        setAccesibleId(getId());
        return accesibleId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getMeasureType() {
        return measureType;
    }

    public String getProductType() {
        return productType;
    }
}
