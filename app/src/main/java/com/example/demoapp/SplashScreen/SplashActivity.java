package com.example.demoapp.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

import com.example.demoapp.LogIn.LogInViewModel;
import com.example.demoapp.LogIn.LoginActivity;
import com.example.demoapp.R;

public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_DURATION = 3000;
    private Context context;
    private SplashViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context = this;
        viewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(SplashViewModel.class);
        initDatabases();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                LoginActivity.navigate((Activity) context);
                finish();
            };
        },SPLASH_DURATION);




    }
    private void initDatabases(){
        viewModel.getProduct("null");
        viewModel.getUser("null");
    }

}
