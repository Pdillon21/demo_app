package com.example.demoapp.LogIn;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import com.example.demoapp.model.User;
import com.example.demoapp.repositories.UserRepository;

import java.util.List;

public class LogInViewModel extends AndroidViewModel {
    private UserRepository repository;
    private List<User> allUsers;

    public LogInViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
        allUsers = repository.getAllUsers();
        repository.getUser("null");
    }

    public List<User> getAllUsers() {
        return allUsers;
    }

    public User getUser (String username){
        List<User> aUserList = repository.getUser(username);
        User aUSer = null;
        if (!aUserList.isEmpty()){
            aUSer = aUserList.get(0);
        }
        return aUSer;
    }


}
