package com.example.mobileproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.adaptor.ListAdapter;
import com.example.mobileproject.databinding.ActivityMainPageBinding;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends AppCompatActivity {

    private List<MyList> myListData;
    private ActivityMainPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        binding.createListBtn.setOnClickListener(v ->
                startActivity(new Intent(MainPage.this, CreateList.class)));

        myListData = new ArrayList<>();
        myListData.add(new MyList("1", "Shopping List", new ArrayList<>()));
        myListData.add(new MyList("2", "Work Tasks", new ArrayList<>()));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ListAdapter(myListData, this));
    }
}