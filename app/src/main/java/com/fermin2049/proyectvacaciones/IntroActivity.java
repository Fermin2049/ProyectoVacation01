package com.fermin2049.proyectvacaciones;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fermin2049.proyectvacaciones.databinding.ActivityIntroBinding;
import com.fermin2049.proyectvacaciones.databinding.ActivityMainBinding;

public class IntroActivity extends BaseActivity {
    ActivityIntroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntroBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.introBtn.setOnClickListener(v -> startActivity(new Intent(IntroActivity.this,MainActivity.class)));


    }
}