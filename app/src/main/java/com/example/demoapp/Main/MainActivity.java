package com.example.demoapp.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demoapp.ProductPicker.ProductPickerActivity;
import com.example.demoapp.R;
import com.example.demoapp.model.User;

public class MainActivity extends AppCompatActivity {
    private CardView buyCardView;
    private CardView addCardView;
    private CardView editCardView;
    private CardView moreCardView;
    private TextView nameTextview;

    private User user;

    private Context context;

    private static final String EXTRA_USER = "extra_user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        user = (User) getIntent().getExtras().get(EXTRA_USER);
        findViews();
        setViews();
    }

    private void findViews() {
        nameTextview = findViewById(R.id.user_first_name_label);
        buyCardView = findViewById(R.id.buy_button);
        addCardView = findViewById(R.id.add_button);
        editCardView = findViewById(R.id.edit_button);
        moreCardView = findViewById(R.id.more_button);
    }

    private void setViews() {
        String greetingString = "Hi, " + user.getFirstName() + "!";

        nameTextview.setText(greetingString);

        buyCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductPickerActivity.navigate((Activity) context);
            }
        });
        addCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sectionUnavailableMessage();
            }
        });
        editCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sectionUnavailableMessage();
            }
        });
        moreCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sectionUnavailableMessage();
            }
        });
    }

    private void sectionUnavailableMessage() {
        Toast.makeText(this, "Section not Available yey", Toast.LENGTH_SHORT).show();
    }

    public static void navigate(Activity activity, User user) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.putExtra(EXTRA_USER, user);
        ActivityCompat.startActivityForResult(activity, intent, 0, null);
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
