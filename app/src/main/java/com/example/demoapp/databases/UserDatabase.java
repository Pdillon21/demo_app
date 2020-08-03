package com.example.demoapp.databases;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.demoapp.DAO.UserDao;
import com.example.demoapp.model.User;

@Database(entities = {User.class},version = 1)
public abstract class UserDatabase extends RoomDatabase {

    private static UserDatabase instance;

    public abstract UserDao userDao();

    // Synchronized will guarantee that only one thread at a time can access this database
    // This is a small Demo App, but for bigger projects that use multi-threading, this will prevent
    // us from ruining our database by performing simultaneous changes.
    public static synchronized UserDatabase getInstance(Context context){
     if (instance == null){
         instance = Room.databaseBuilder(context.getApplicationContext(),
                 UserDatabase.class, "user_table")
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

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private UserDao userDao;

        private PopulateDbAsyncTask (UserDatabase db){
            userDao = db.userDao();

        }
        @Override
        protected Void doInBackground(Void... voids) {
            userDao.insert(new User("Juan","Sin Nombre", "admin","admin"));
            return null;
        }
    }


}
