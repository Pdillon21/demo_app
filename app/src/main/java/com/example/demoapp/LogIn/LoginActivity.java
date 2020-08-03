package com.example.demoapp.LogIn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demoapp.entities.FailedLoginErrors;
import com.example.demoapp.Main.MainActivity;
import com.example.demoapp.R;
import com.example.demoapp.SignUp.SignUpActivity;
import com.example.demoapp.model.User;


public class LoginActivity extends AppCompatActivity {

    TextView signUpClickableLabel;

    EditText usernameEditText;

    EditText passwordEditText;

    CardView logInButton;

    private boolean isUsernameEmpty = true;
    private boolean isPasswordEmpty = true;
    private Context context;
    private LogInViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_login);
        setUpViews();

        viewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(LogInViewModel.class);

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(inputIsValid()){
                   User user = viewModel.getUser(usernameEditText.getText().toString().trim());
                   if (user == null){
                       Toast.makeText(context, "Not a registered User", Toast.LENGTH_SHORT).show();
                   } else {
                       if (passwordEditText.getText().toString().trim().equals(user.getPassword())){
                           MainActivity.navigate((Activity) context, user);
                       } else {
                           Toast.makeText(context, "Wrong Password", Toast.LENGTH_SHORT).show();
                       }
                   }
               }
            }
        });
        signUpClickableLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUpActivity.navigate((Activity) context);
            }
        });

    }

    private boolean inputIsValid() {
        if (usernameEditText.getText().toString().isEmpty()){
            isUsernameEmpty = true;
        } else {
            isUsernameEmpty = false;
        }

        if (passwordEditText.getText().toString().isEmpty()){
            isPasswordEmpty = true;
        } else {
            isPasswordEmpty = false;
        }


        if (!isPasswordEmpty && !isUsernameEmpty){
            return true;
        } else {
            if (isPasswordEmpty && !isUsernameEmpty){
                //There's input for Username, but not for Password
                Toast.makeText(context, "Password can´t be empty", Toast.LENGTH_SHORT).show();
                return false;
            } else if (!isPasswordEmpty && isUsernameEmpty){
                //There's input for Password, but not for Username
                Toast.makeText(context, "User Name can´t be empty", Toast.LENGTH_SHORT).show();
                return false;
            } else {
                //There's no input
                Toast.makeText(context, "User Name and Password can´t be empty", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
    }
    private void setUpViews() {
        signUpClickableLabel = findViewById(R.id.sign_up_clickable_label);

        usernameEditText = findViewById(R.id.username_edit_text);

        passwordEditText = findViewById(R.id.password_edit_text);

        logInButton = findViewById(R.id.log_in_button);
    }

    public static void navigate (Activity activity){
        Intent intent = new Intent(activity,LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        ActivityCompat.startActivityForResult(activity,intent,0,null);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    public void failedLoginAttempt(FailedLoginErrors error) {
        //ToDo
        switch (error){
            case WRONG_PASSWORD:
                Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT).show();
                break;
            case USERNAME_DONT_EXIST:
                Toast.makeText(this, "Not a registered User", Toast.LENGTH_SHORT).show();
                break;
        }


    }
}
