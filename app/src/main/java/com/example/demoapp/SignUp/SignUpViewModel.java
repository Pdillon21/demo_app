package com.example.demoapp.SignUp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.demoapp.Main.MainActivity;
import com.example.demoapp.model.User;
import com.example.demoapp.repositories.UserRepository;

import java.util.List;

public class SignUpViewModel extends AndroidViewModel {

    private UserRepository repository;


    public SignUpViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
    }

    public void performUserRegistration(User user){
        repository.insert(user);

    }

    public List<User> getUser (String username){
        return repository.getUser(username);
    }


    public void checkUserRegisteredAndLogin(User user, SignUpActivity anActivity) {
        List<User> aUserList = repository.getUser(user.getUserName());
        try {
            User aUser = aUserList.get(0);
            MainActivity.navigate(anActivity,aUser);
        } catch (Exception e){
            anActivity.failedToLogin();
        }




    }

}
