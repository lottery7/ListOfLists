package com.example.mobileproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mobileproject.databinding.ActivityAddItemBinding;
import com.example.mobileproject.databinding.ActivityMainPageBinding;

public class AddItem extends AppCompatActivity {


    private ActivityAddItemBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        binding.addItemBtn.setOnClickListener(v -> startActivity(new Intent(AddItem.this, CreateList.class)));
    }
}