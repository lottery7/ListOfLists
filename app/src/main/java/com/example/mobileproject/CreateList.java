package com.example.mobileproject;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.adaptor.ItemAdaptor;
import com.example.mobileproject.databinding.ActivityCreateListBinding;

import java.util.ArrayList;

public class CreateList extends AppCompatActivity {

    private String newItem;
    private ArrayList<String> itemList; // список элементов
    private ItemAdaptor itemAdaptor; // адаптер

    private ActivityCreateListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        // Инициализация списка и адаптера
        itemList = new ArrayList<>();
        itemAdaptor = new ItemAdaptor(itemList);

        // Настройка RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(itemAdaptor);

        // Обработчик кнопки для добавления нового элемента
        binding.button3.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Add item");

            final EditText input = new EditText(this);
            builder.setView(input);

            builder.setPositiveButton("OK", (dialog, which) -> {
                newItem = input.getText().toString();
                if (!newItem.isEmpty()) {
                    itemList.add(newItem); // добавляем новый элемент в список
                    itemAdaptor.notifyItemInserted(itemList.size() - 1); // обновляем адаптер
                }
            });

            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
            builder.show();
        });
    }
}
