package com.example.grocery;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grocery.activitys.PlacedOrderActivity;
import com.example.grocery.adapters.MyCartAdapter;
import com.example.grocery.models.MycartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyCartsFragment extends Fragment {

    RecyclerView rec_cart;
    MyCartAdapter rec_adapter;
    List<MycartModel> mycartModelList;
     TextView overTotalAmount;
    ProgressBar progressBar;
    Button buynow;
//    CalculateTotal calculatecall;

    FirebaseFirestore db;
    FirebaseAuth auth;

    public MyCartsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_my_carts, container, false);


        progressBar=root.findViewById(R.id.progressBar_cart);
        progressBar.setVisibility(View.VISIBLE);
        buynow=root.findViewById(R.id.buy_now);
        overTotalAmount=root.findViewById(R.id.overTotalAmount);

        db=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        rec_cart=root.findViewById(R.id.rec_cart);
        rec_cart.setVisibility(View.GONE);


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        rec_cart.setLayoutManager(linearLayoutManager);
        mycartModelList=new ArrayList<>();
        rec_adapter=new MyCartAdapter(getActivity(),mycartModelList);
        rec_cart.setAdapter(rec_adapter);



        overTotalAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTotal(mycartModelList);
            }
        });

        db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){

                        String DocumentId= documentSnapshot.getId();
                        MycartModel model=documentSnapshot.toObject(MycartModel.class);

                        model.setDocumentId(DocumentId);
                        mycartModelList.add(model);
                        rec_adapter.notifyDataSetChanged();

                    }
                    if (mycartModelList == null){
                        progressBar.setVisibility(View.GONE);
                    }
                    else{
                        progressBar.setVisibility(View.GONE);
                        rec_cart.setVisibility(View.VISIBLE);
                         calculateTotal(mycartModelList);
//                        calculatecall=new CalculateTotal();
//                        double totalAmount = calculatecall.calculateTotal(mycartModelList);
//                        overTotalAmount.setText("Total Amount :"+ totalAmount);


                    }


                }
                else{
                    Toast.makeText(getContext(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                }

            }
        });


        buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mycartModelList.size()>0) {
                    Intent intent = new Intent(getContext(), PlacedOrderActivity.class);
                    intent.putExtra("itemList", (Serializable) mycartModelList);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getContext(), "You Not add any item to your Cart", Toast.LENGTH_SHORT).show();
                }
            }
        });




        return root;
    }

    public Object calculateTotal(List<MycartModel> mycartModelList) {
        double totalAmount = 0;

        for (MycartModel mycartModel : mycartModelList){
            totalAmount += mycartModel.getTotalPrice();
        }
        overTotalAmount.setText("Total Amount :"+ totalAmount);

        return null;
    }




}