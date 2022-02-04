package com.example.grocery.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.grocery.R;
import com.example.grocery.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailedActivity extends AppCompatActivity {

    ImageView detailed_image,addbtn,removebtn;
    TextView price,rating,description,quantity;
    Button add_to_cart;
    Toolbar toolbar;
    int totalquantity=1;
    int totalprice;
    ViewAllModel viewAllModel = null;

    FirebaseFirestore firestore;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ditailed);
        getSupportActionBar().hide();

        auth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();

        final Object object=getIntent().getSerializableExtra("detailed");

        if (object instanceof ViewAllModel){
            viewAllModel = (ViewAllModel) object;
        }

        detailed_image=findViewById(R.id.detailed_img);
        addbtn=findViewById(R.id.detailed_add);
        removebtn=findViewById(R.id.detailed_remove);
        price=findViewById(R.id.detailed_price);
        rating=findViewById(R.id.detailed_rating);
        description=findViewById(R.id.detailed_description);
        quantity=findViewById(R.id.detailed_quantity);
        add_to_cart=findViewById(R.id.add_to_cart);

        if (object != null){
            Glide.with(getApplicationContext()).load(viewAllModel.getImg_uri()).placeholder(R.drawable.placeholder).into(detailed_image);
            rating.setText(viewAllModel.getRating());
            price.setText(viewAllModel.getPrice()+"/kg");
            description.setText(viewAllModel.getDescription());

            totalprice = viewAllModel.getPrice() * totalquantity;

            if (viewAllModel.getType().equalsIgnoreCase("milk")){
                price.setText(viewAllModel.getPrice()+"/liter");
                totalprice = viewAllModel.getPrice() * totalquantity;
            }
            if (viewAllModel.getType().equalsIgnoreCase("egg")){
                price.setText(viewAllModel.getPrice()+"/dozen");
                totalprice = viewAllModel.getPrice() * totalquantity;
            }

        }

        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addedToCart();
            }
        });

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalquantity<10){
                    totalquantity++;
                    quantity.setText(String.valueOf(totalquantity));
                    totalprice = viewAllModel.getPrice() * totalquantity;
                }

            }
        });
        removebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalquantity>1){
                    totalquantity--;
                    quantity.setText(String.valueOf(totalquantity));
                    totalprice = viewAllModel.getPrice() * totalquantity;
                }
            }
        });



    }

    private void addedToCart() {
        String savecurrentDate,saveCurrentTime;
        Calendar caleForDate=Calendar.getInstance();

        SimpleDateFormat currentDate=new SimpleDateFormat("dd-MM-yyyy");
        savecurrentDate=currentDate.format(caleForDate.getTime());

        SimpleDateFormat currentTime=new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime=currentTime.format(caleForDate.getTime());

        final HashMap<String,Object> cartMap=new HashMap<>();

        cartMap.put("ProductName",viewAllModel.getName());
        cartMap.put("ProductPrice",price.getText().toString());
        cartMap.put("CurrentDate",savecurrentDate);
        cartMap.put("CurrentTime",saveCurrentTime);
        cartMap.put("TotalQuantity",quantity.getText().toString());
        cartMap.put("TotalPrice",totalprice);

        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(DetailedActivity.this, "Added To A Cart", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}