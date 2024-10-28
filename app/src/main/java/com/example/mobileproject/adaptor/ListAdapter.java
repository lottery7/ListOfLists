package com.example.mobileproject.adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.CreateList;
import com.example.mobileproject.MyList;
import com.example.mobileproject.R;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<MyList> myLists;
    private Context context;

    public ListAdapter(List<MyList> myLists, Context context) {
        this.myLists = myLists;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mylist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyList myList = myLists.get(position);
        holder.textViewName.setText(myList.name);

        // Обработчик клика на элемент списка
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, CreateList.class);
            intent.putExtra("list_uid", myList.uid); // Передача UID в CreateList
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return myLists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.list_name);
        }
    }
}
