package com.example.grocery.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.grocery.R;
import com.example.grocery.models.RecommendedModel;

import java.util.List;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.ViewHolder> {
    Context context;
    List<RecommendedModel> recommendedModelList;

    public RecommendedAdapter(Context context, List<RecommendedModel> recommendedModelList) {
        this.context = context;
        this.recommendedModelList = recommendedModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recommended_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         Glide.with(context).load(recommendedModelList.get(position).getRec_img()).into(holder.rec_img);
         holder.rec_name.setText(recommendedModelList.get(position).getRec_name());
         holder.rec_rating.setText(recommendedModelList.get(position).getRec_rating());
         holder.rec_dec.setText(recommendedModelList.get(position).getRec_dec());


    }

    @Override
    public int getItemCount() {
        return recommendedModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView rec_img;
        TextView rec_name;
        TextView rec_dec;
        TextView rec_rating;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rec_img=itemView.findViewById(R.id.rec_img);
            rec_dec=itemView.findViewById(R.id.rec_dec);
            rec_name=itemView.findViewById(R.id.rec_name);
            rec_rating=itemView.findViewById(R.id.rec_rating);

        }
    }
}
