package com.example.grocery.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.grocery.R;
import com.example.grocery.models.MycartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlacedOrderActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseFirestore firestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placed_order);
        firestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();

        getSupportActionBar().hide();
        List<MycartModel> list = (ArrayList<MycartModel>) getIntent().getSerializableExtra("itemList");

        if (list != null && list.size()>0){
            for (MycartModel model :list){
            final HashMap<String,Object> buyMap=new HashMap<>();

            buyMap.put("ProductName",model.getProductName());
            buyMap.put("ProductPrice",model.getProductPrice());
            buyMap.put("CurrentDate",model.getCurrentDate());
            buyMap.put("CurrentTime",model.getCurrentTime());
            buyMap.put("TotalQuantity",model.getTotalQuantity());
            buyMap.put("TotalPrice",model.getTotalPrice());

            firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                    .collection("MyOrder").add(buyMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {

                }
            });
        }
    }
    }
}