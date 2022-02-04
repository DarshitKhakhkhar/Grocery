package com.example.grocery.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grocery.R;
import com.example.grocery.adapters.NavCategoryDetailedAdapter;
import com.example.grocery.models.NavCategoryDetailedModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class NavCatagoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<NavCategoryDetailedModel> list;
    NavCategoryDetailedAdapter navCategoryDetailedAdapter;
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    ProgressBar progressBar;
    TextView text;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_catagory);
        getSupportActionBar().hide();
        progressBar=findViewById(R.id.progressBar_nav_cat);
        progressBar.setVisibility(View.VISIBLE);
        text=findViewById(R.id.view_all_text);
        imageView=findViewById(R.id.view_all_img);

        String type=getIntent().getStringExtra("type");

        recyclerView=findViewById(R.id.nav_detail_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setVisibility(View.GONE);

        list = new ArrayList<>();
        navCategoryDetailedAdapter=new NavCategoryDetailedAdapter(this,list);
        recyclerView.setAdapter(navCategoryDetailedAdapter);


        firestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();


        if (type != null && type.equalsIgnoreCase("drinks")) {
            firestore.collection("NavCategoryDetailed").whereEqualTo("type","drinks")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                    NavCategoryDetailedModel navCategoryDetailedModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                                    list.add(navCategoryDetailedModel);
                                    navCategoryDetailedAdapter.notifyDataSetChanged();
                                    progressBar.setVisibility(View.GONE);
                                    recyclerView.setVisibility(View.VISIBLE);
                                }
                            } else {
                                Toast.makeText(NavCatagoryActivity.this, "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        if (type !=null && !type.equalsIgnoreCase("drinks")){
            progressBar.setVisibility(View.GONE);
            text.setText("Coming Soon");
            imageView.setVisibility(View.VISIBLE);


        }


    }
}