package com.example.grocery.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.grocery.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Activity extends AppCompatActivity {

    ActivityLoginBinding binding;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        auth=FirebaseAuth.getInstance();


        binding.alreadysignuptv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login_Activity.this, SignUp_Activity.class);
                startActivity(intent);
            }
        });


        binding.loginpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               loginuser();
            }
        });
    }

    private void loginuser() {

        String password=binding.passwordlogintv.getText().toString();
        String email=binding.emaillogintv.getText().toString();


        if(email.isEmpty()){
            Toast.makeText(this, "email is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.isEmpty()){
            Toast.makeText(this, "password is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.length()<6){
            Toast.makeText(this, "password Length Must Be Greater then 6", Toast.LENGTH_SHORT).show();
            return;
        }
        // login user

        auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Login_Activity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login_Activity.this, MainActivity.class));
                            finish();
                        }
                        else {
                            Toast.makeText(Login_Activity.this,"Please enter Valid Email and Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}