package com.fermin2049.proyectvacaciones.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;

import com.fermin2049.proyectvacaciones.Adapter.CategoryAdapter;
import com.fermin2049.proyectvacaciones.Adapter.PopularAdapter;
import com.fermin2049.proyectvacaciones.Adapter.RecommendedAdapter;
import com.fermin2049.proyectvacaciones.Adapter.SliderAdapter;
import com.fermin2049.proyectvacaciones.Domain.Category;
import com.fermin2049.proyectvacaciones.Domain.ItemDomain;
import com.fermin2049.proyectvacaciones.Domain.Location;
import com.fermin2049.proyectvacaciones.Domain.SliderItems;
import com.fermin2049.proyectvacaciones.R;
import com.fermin2049.proyectvacaciones.databinding.ActivityMainBinding;
import com.google.android.material.datepicker.CompositeDateValidator;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        initLocation();
        initBanners();
        initCategory();
        initRecommended();
        initPopular();

    }

    private void initPopular() {
        DatabaseReference myRef = database.getReference("Popular");
        binding.progressBarPopular.setVisibility(View.VISIBLE);
        ArrayList<ItemDomain> list = new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot issue:snapshot.getChildren()){
                        list.add(issue.getValue(ItemDomain.class));
                    }
                    if (!list.isEmpty()){
                        binding.recyclerViewPopular.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
                        RecyclerView.Adapter adapter = new PopularAdapter(list);
                        binding.recyclerViewPopular.setAdapter(adapter);

                    }
                    binding.progressBarPopular.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initRecommended() {
        DatabaseReference myRef = database.getReference("Item");
        binding.progressBarRecommended.setVisibility(View.VISIBLE);
        ArrayList<ItemDomain> list = new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot issue:snapshot.getChildren()){
                        list.add(issue.getValue(ItemDomain.class));
                    }
                    if (!list.isEmpty()){
                        binding.recyclerViewRecommended.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
                        RecyclerView.Adapter adapter = new RecommendedAdapter(list);
                        binding.recyclerViewRecommended.setAdapter(adapter);

                    }
                    binding.progressBarRecommended.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initCategory() {
        DatabaseReference myRef = database.getReference("Category");
        binding.progressBarCategory.setVisibility(View.VISIBLE);
        ArrayList<Category> list = new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot issue:snapshot.getChildren()){
                        list.add(issue.getValue(Category.class));
                    }
                    if (!list.isEmpty()){
                        binding.recyclerViewCategory.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
                        RecyclerView.Adapter adapter = new CategoryAdapter(list);
                        binding.recyclerViewCategory.setAdapter(adapter);
                    }
                    binding.progressBarCategory.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initLocation() {
        DatabaseReference myRef = database.getReference("Location");
        ArrayList<Location> list = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot issue:snapshot.getChildren()){
                        list.add(issue.getValue(Location.class));
                    }
                    ArrayAdapter<Location> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.sp_item,list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.locationSp.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void banners(ArrayList<SliderItems> item){
        binding.viewPagerSladers.setAdapter(new SliderAdapter(item,binding.viewPagerSladers));
        binding.viewPagerSladers.setClipToPadding(false);
        binding.viewPagerSladers.setClipChildren(false);
        binding.viewPagerSladers.setOffscreenPageLimit(3);
        binding.viewPagerSladers.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        binding.viewPagerSladers.setPageTransformer(compositePageTransformer);
    }

    private void initBanners(){
        DatabaseReference myRef = database.getReference("Banner");
        binding.progressBarBanner.setVisibility(View.VISIBLE);
        ArrayList<SliderItems> item = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot issue : snapshot.getChildren()){
                        item.add(issue.getValue(SliderItems.class));
                    }
                    banners(item);
                    binding.progressBarBanner.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}