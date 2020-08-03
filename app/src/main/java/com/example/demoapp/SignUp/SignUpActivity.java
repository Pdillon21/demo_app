package com.example.demoapp.SignUp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demoapp.LogIn.LoginActivity;
import com.example.demoapp.Main.MainActivity;
import com.example.demoapp.R;
import com.example.demoapp.model.User;

public class SignUpActivity extends AppCompatActivity {

    private TextView logInClickableTextview;
    private EditText firsNameEditText;
    private EditText lastNameEditText;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button signUpButton;

    private Context context;

    private SignUpViewModel viewmodel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_sign_up);
        setUpViews();
        viewmodel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(SignUpViewModel.class);
        logInClickableTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.navigate((Activity) context);
            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidInput();
            }
        });
    }

    private void checkValidInput() {
        boolean isUserNameEmpty = true;
        boolean isPasswordEmpty = true;
        boolean isFirstNameEmpty = true;
        boolean isLastNameEmpty = true;
        if (!firsNameEditText.getText().toString().isEmpty()){
            isFirstNameEmpty = false;
        }
        if (!lastNameEditText.getText().toString().isEmpty()){
            isLastNameEmpty = false;
        }
        if (!passwordEditText.getText().toString().isEmpty()){
            isPasswordEmpty = false;
        }
        if (!usernameEditText.getText().toString().isEmpty()){
            isUserNameEmpty = false;
        }
        if (isFirstNameEmpty || isLastNameEmpty || isUserNameEmpty || isPasswordEmpty){
            Toast.makeText(context, "All fields need to be completed", Toast.LENGTH_SHORT).show();
        } else {
            User aProspectUser = null;
            try {
                aProspectUser = viewmodel.getUser(usernameEditText.getText().toString().trim()).get(0);
                Toast.makeText(context, "Sorry, that username is already taken", Toast.LENGTH_SHORT).show();
            } catch (Exception e){
                registerUser();
            }
        }

    }

    private void registerUser() {
        User newUser = new User(firsNameEditText.getText().toString(),
                lastNameEditText.getText().toString(),
                usernameEditText.getText().toString(),
                passwordEditText.getText().toString());
        viewmodel.performUserRegistration(newUser);
        LoginActivity.navigate(this);
    }

    private void logIn(User user){
        MainActivity.navigate(this,user);
    }

    public static void navigate (Activity activity){
        Intent intent = new Intent(activity,SignUpActivity.class);
        ActivityCompat.startActivityForResult(activity,intent,0,null);

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    private void setUpViews() {
        logInClickableTextview = findViewById(R.id.log_in_clickable_label);
        firsNameEditText = findViewById(R.id.first_name);
        lastNameEditText = findViewById(R.id.last_name);
        usernameEditText = findViewById(R.id.username_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        signUpButton = findViewById(R.id.sign_up_button);
    }

    public void failedToLogin() {
        Toast.makeText(context, "Sorry, couldn't perform login", Toast.LENGTH_SHORT).show();
    }
}
