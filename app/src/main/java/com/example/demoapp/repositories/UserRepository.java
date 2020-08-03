package com.example.demoapp.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.example.demoapp.DAO.UserDao;
import com.example.demoapp.databases.UserDatabase;
import com.example.demoapp.model.User;

import java.util.List;

public class UserRepository {

    private UserDao userDao;
    private List<User> allUsers;

    public UserRepository (Application application){
        UserDatabase userDatabase = UserDatabase.getInstance(application);
        userDao = userDatabase.userDao();
        userDao.getAllUsers();
    }

    public void insert (User user){
        new InsertUserAsyncTask(userDao).execute(user);
    }

    public void update (User user){
        new UpdateUserAsyncTask(userDao).execute(user);
    }

    public void delete (User user){
        new DeleteUserAsyncTask(userDao).execute(user);
    }

    public List<User> getAllUsers (){
        return allUsers;
    }

    public List<User> getUser (String userName){
       return userDao.getUser(userName);
    }


    private static class InsertUserAsyncTask extends AsyncTask<User,Void,Void>{
        private UserDao userDao;

        private InsertUserAsyncTask(UserDao userDao){
            this.userDao = userDao;
        }
        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<User,Void,Void>{
        private UserDao userDao;

        private UpdateUserAsyncTask(UserDao userDao){
            this.userDao = userDao;
        }
        @Override
        protected Void doInBackground(User... users) {
            userDao.update(users[0]);
            return null;
        }
    }

    private static class DeleteUserAsyncTask extends AsyncTask<User,Void,Void>{
        private UserDao userDao;

        private DeleteUserAsyncTask(UserDao userDao){
            this.userDao = userDao;
        }
        @Override
        protected Void doInBackground(User... users) {
            userDao.delete(users[0]);
            return null;
        }
    }
}
