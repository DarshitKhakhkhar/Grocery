package com.example.grocery.activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.grocery.databinding.ActivitySignUpBinding;
import com.example.grocery.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp_Activity extends AppCompatActivity {
ActivitySignUpBinding binding;
FirebaseAuth auth;
FirebaseDatabase database;
ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();

        progressDialog=new ProgressDialog(SignUp_Activity.this);
        progressDialog.setTitle("please Wait");
        progressDialog.setMessage("Creating User.....");


        binding.alreadysignintv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignUp_Activity.this, Login_Activity.class);
                startActivity(intent);
            }
        });


        binding.signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();

            }
        });
    }

    private void createUser() {
        String username=binding.nametv.getText().toString();
        String password=binding.passwordtv.getText().toString();
        String email=binding.emailtv.getText().toString();

        if(username.isEmpty()){
            Toast.makeText(this, "Name is Empty", Toast.LENGTH_SHORT).show();
            return;
        }

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
        progressDialog.show();

        // create User

        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                   if (task.isSuccessful()){
                       progressDialog.dismiss();

                       UserModel userModel=new UserModel(username,email,password);
                       String id=task.getResult().getUser().getUid();
                       database.getReference().child("Users").child(id).setValue(userModel);

                       Toast.makeText(SignUp_Activity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                      startActivity(new Intent(SignUp_Activity.this, MainActivity.class));
                      finish();

                   }
                   else {
                       progressDialog.dismiss();
                       Toast.makeText(SignUp_Activity.this, "Error:"+task.getException(), Toast.LENGTH_SHORT).show();
                   }
                    }
                });
    }
}