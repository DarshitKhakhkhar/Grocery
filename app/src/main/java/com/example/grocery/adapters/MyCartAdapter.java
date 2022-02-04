package com.example.grocery.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grocery.MyCartsFragment;
import com.example.grocery.R;
import com.example.grocery.models.MycartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {

    Context context;
    List<MycartModel> mycartModelList;
    double totalamount=0;
    FirebaseAuth auth;
    FirebaseFirestore firestore;




    public MyCartAdapter(Context context, List<MycartModel> mycartModelList) {
        this.context = context;
        this.mycartModelList = mycartModelList;
        auth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();

    }

    @NonNull
    @Override
    public MyCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyCartAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.name.setText(mycartModelList.get(position).getProductName());
        holder.time.setText(mycartModelList.get(position).getCurrentTime());
        holder.quantity.setText(mycartModelList.get(position).getTotalQuantity());
        holder.date.setText(mycartModelList.get(position).getCurrentDate());
        holder.price.setText(mycartModelList.get(position).getProductPrice());
        holder.totalPrice.setText(String.valueOf(mycartModelList.get(position).getTotalPrice()));


        holder.clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("AddToCart")
                        .document(mycartModelList.get(position).getDocumentId())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    mycartModelList.remove(mycartModelList.get(position));
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Item Deleted Successfully", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(context, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


    }

    @Override
    public int getItemCount() {
        return mycartModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,price,quantity,totalPrice,date,time;
        ImageView clear;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.product_p_name);
            price=itemView.findViewById(R.id.product_p_price);
            quantity=itemView.findViewById(R.id.product_quantity);
            totalPrice=itemView.findViewById(R.id.product_total_price);
            date=itemView.findViewById(R.id.product_p_date);
            time=itemView.findViewById(R.id.product_p_time);
            clear=itemView.findViewById(R.id.delete_cart);

        }
    }
}
