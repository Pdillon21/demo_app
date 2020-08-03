package com.example.demoapp.databases;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.demoapp.DAO.ProductDao;
import com.example.demoapp.entities.ProductMeasureType;
import com.example.demoapp.entities.ProductType;
import com.example.demoapp.model.Product;


@Database(entities = {Product.class},version = 2)
public abstract class ProductDatabase extends RoomDatabase {

    private static ProductDatabase instance;

    public abstract ProductDao productDao();


    public static synchronized ProductDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ProductDatabase.class, "product_table")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;

    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate (@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();

        }

    };
    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void> {
        private ProductDao productDao;

        private PopulateDbAsyncTask (ProductDatabase db){
            productDao = db.productDao();

        }
        @Override
        protected Void doInBackground(Void... voids) {
            productDao.insert(new Product("Bowl of nothin","Porcelain made, contains your current job prospects.",0.,"", ProductMeasureType.UNIT_BASED_PRODUCT.toString(), ProductType.OTHER.toString()));
            productDao.insert(new Product("iBowl of nothin","Porcelain made, contains your current job prospects. by Aple.inc.",100.,"", ProductMeasureType.UNIT_BASED_PRODUCT.toString(), ProductType.OTHER.toString()));
            productDao.insert(new Product("Quilmes 1L","Cheap beer. Bad for your health, good for your wallet.",2.,"", ProductMeasureType.LITERS_BASED_PRODUCT.toString(), ProductType.ALCOHOL_BEVERAGE.toString()));
            productDao.insert(new Product("Instant noodles","For when you really don't feel like cooking.",4.2,"", ProductMeasureType.UNIT_BASED_PRODUCT.toString(), ProductType.OTHER.toString()));
            productDao.insert(new Product("Cheetos","Your keyboard is gonna get messy.",2.5,"", ProductMeasureType.UNIT_BASED_PRODUCT.toString(), ProductType.OTHER.toString()));
            productDao.insert(new Product("Onion flavoured chips","Your personality should be enough, but need to make sure no one approaches you.",3.2,"", ProductMeasureType.UNIT_BASED_PRODUCT.toString(), ProductType.OTHER.toString()));
            productDao.insert(new Product("Bath Satls","Not the ones you think, it's the boring ones.",14.,"", ProductMeasureType.UNIT_BASED_PRODUCT.toString(), ProductType.OTHER.toString()));
            productDao.insert(new Product("Another bowl of nothin","Making up products is quite hard.",123.,"", ProductMeasureType.UNIT_BASED_PRODUCT.toString(), ProductType.OTHER.toString()));
            productDao.insert(new Product("Eventually - ","you'll be able to add products, an set an image url, that will be retrieved with glide. ",1986.,"", ProductMeasureType.UNIT_BASED_PRODUCT.toString(), ProductType.OTHER.toString()));

            return null;
        }
    }





}
