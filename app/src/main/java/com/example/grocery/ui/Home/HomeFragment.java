package com.example.grocery.ui.Home;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grocery.R;
import com.example.grocery.adapters.HomeAdapter;
import com.example.grocery.adapters.PopularAdapter;
import com.example.grocery.adapters.RecommendedAdapter;
import com.example.grocery.databinding.FragmentHomeBinding;
import com.example.grocery.models.HomeCategory;
import com.example.grocery.models.PopularModel;
import com.example.grocery.models.RecommendedModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    RecyclerView popularrec,homecatrec,recommendedrec;
    ProgressBar progressBar;
    ScrollView scrollView;

    //popular item
    List<PopularModel> popularModelList;
    PopularAdapter popularAdapter;

    //HomeCategory
    List<HomeCategory>homeCategoryList;
    HomeAdapter homeAdapter;

    //Recommended
    List<RecommendedModel>recommendedModelList;
    RecommendedAdapter recommendedAdapter;

    FirebaseFirestore db;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        popularrec=root.findViewById(R.id.popular_rec);
        homecatrec=root.findViewById(R.id.explore_rec);
        recommendedrec=root.findViewById(R.id.recommended_rec);
        scrollView=root.findViewById(R.id.scroll_view);
        progressBar=root.findViewById(R.id.progressbar);

        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        db=FirebaseFirestore.getInstance();

        //popular item

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        popularrec.setLayoutManager(linearLayoutManager);
        popularModelList =new ArrayList<>();
        popularAdapter = new PopularAdapter(getActivity(),popularModelList);
        popularrec.setAdapter(popularAdapter);


        db.collection("PopularProduct")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                       if (task.isSuccessful()){
                           for (QueryDocumentSnapshot document : task.getResult()){
                               PopularModel popularModel=document.toObject(PopularModel.class);
                               popularModelList.add(popularModel);
                               popularAdapter.notifyDataSetChanged();
                               progressBar.setVisibility(View.GONE);
                               scrollView.setVisibility(View.VISIBLE);
                           }
                       }else {
                           Toast.makeText(getActivity(), "Error:"+task.getException(), Toast.LENGTH_SHORT).show();

                       }
                    }
                });


        //Explore category
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        homecatrec.setLayoutManager(layoutManager);
        homeCategoryList=new ArrayList<>();
        homeAdapter=new HomeAdapter(getActivity(),homeCategoryList);
        homecatrec.setAdapter(homeAdapter);

        db.collection("HomeCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){
                                HomeCategory homeCategory=document.toObject(HomeCategory.class);
                                homeCategoryList.add(homeCategory);
                                homeAdapter.notifyDataSetChanged();
                            }
                        }else {
                            Toast.makeText(getActivity(), "Error:"+task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

        //recommended

        LinearLayoutManager layoutManager1=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recommendedrec.setLayoutManager(layoutManager1);
        recommendedModelList=new ArrayList<>();
        recommendedAdapter=new RecommendedAdapter(getActivity(),recommendedModelList);
        recommendedrec.setAdapter(recommendedAdapter);

        db.collection("Recommended")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){
                                RecommendedModel recommendedModel=document.toObject(RecommendedModel.class);
                                recommendedModelList.add(recommendedModel);
                                recommendedAdapter.notifyDataSetChanged();
                            }
                        }else {
                            Toast.makeText(getActivity(), "Error:"+task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });




        return root;
    }

}