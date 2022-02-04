package com.example.grocery.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.grocery.databinding.ActivityWelcomeBinding;
import com.google.firebase.auth.FirebaseAuth;

public class Welcome_activity extends AppCompatActivity {
    ActivityWelcomeBinding binding;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityWelcomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();


        auth=FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null){

            startActivity(new Intent(Welcome_activity.this, MainActivity.class));
            finish();
        }

        binding.loginwelcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Welcome_activity.this, Login_Activity.class);
                startActivity(intent);
                finish();

            }
        });

        binding.loginregistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Welcome_activity.this, SignUp_Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}