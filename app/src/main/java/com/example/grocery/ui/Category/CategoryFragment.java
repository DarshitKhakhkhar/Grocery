package com.example.grocery.ui.Category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grocery.R;
import com.example.grocery.adapters.CategoryAdapter;
import com.example.grocery.databinding.FragmentCategoryBinding;
import com.example.grocery.models.CategoryModel;
import com.example.grocery.models.PopularModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends Fragment {

    private FragmentCategoryBinding binding;
    RecyclerView cat_rec;
    List<CategoryModel>categoryModelList;
    CategoryAdapter categoryAdapter;

    ProgressBar progressBarCat;
    ScrollView scrollViewCat;

    FirebaseFirestore db;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCategoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        cat_rec=root.findViewById(R.id.cat_rec);
        progressBarCat=root.findViewById(R.id.progressbar_category);
        scrollViewCat=root.findViewById(R.id.scroll_view_category);

        db=FirebaseFirestore.getInstance();

        progressBarCat.setVisibility(View.VISIBLE);
        scrollViewCat.setVisibility(View.GONE);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        cat_rec.setLayoutManager(linearLayoutManager);
        categoryModelList = new ArrayList<>();
        categoryAdapter=new CategoryAdapter(getActivity(),categoryModelList);
        cat_rec.setAdapter(categoryAdapter);

        db.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){
                                CategoryModel categoryModel=document.toObject(CategoryModel.class);
                                categoryModelList.add(categoryModel);
                                categoryAdapter.notifyDataSetChanged();
                                progressBarCat.setVisibility(View.GONE);
                                scrollViewCat.setVisibility(View.VISIBLE);

                            }
                        }else {
                            Toast.makeText(getActivity(), "Error:"+task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });



        return root;
    }
}