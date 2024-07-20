package com.fermin2049.proyectvacaciones.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.fermin2049.proyectvacaciones.Domain.ItemDomain;
import com.fermin2049.proyectvacaciones.R;
import com.fermin2049.proyectvacaciones.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;
    private ItemDomain objet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntenExtra();
        setVariable();

    }

    private void setVariable() {
        binding.titleTxt.setText(objet.getTitle());
        binding.priceTxt.setText("$" + objet.getPrice());
        binding.backBtn.setOnClickListener(v -> finish());
        binding.bedTxt.setText("" + objet.getBed());
        binding.durationTxt.setText(objet.getDuration());
        binding.distanceTxt.setText(objet.getDistance() + "");
        binding.DescripcionTxt.setText(objet.getDescription());
        binding.addressTxt.setText(objet.getAddress());
        binding.ratingTxt.setText(objet.getScore() + " Rating");
        binding.ratingBar.setRating((float) objet.getScore());

        Glide.with(DetailActivity.this).load(objet.getPic()).into(binding.pic);

        binding.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(DetailActivity.this,)
            }
        });
    }

    private void getIntenExtra() {
        objet = (ItemDomain) getIntent().getSerializableExtra("object");
    }
}