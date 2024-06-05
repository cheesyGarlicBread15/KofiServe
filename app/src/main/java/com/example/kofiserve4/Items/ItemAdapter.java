package com.example.kofiserve4.Items;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kofiserve4.Interfaces.OnItemClick;
import com.example.kofiserve4.R;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    Context context;
    ArrayList<ItemContainer> items;
    OnItemClick itemClickListener;

    public ItemAdapter(Context context, ArrayList<ItemContainer> items, OnItemClick itemClickListener) {
        this.context = context;
        this.items = items;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final ItemViewHolder itemVh = new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_container, parent, false));
        itemVh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(items.get(itemVh.getAdapterPosition()), itemVh.getAdapterPosition(), context, 0);
            }
        });
        return itemVh;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.getNameView().setText(items.get(position).getItem().getName());
        holder.getCategoryView().setText(items.get(position).getItem().getCategory());
        holder.getPriceView().setText(String.valueOf("₱" + items.get(position).getItem().getPrice()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
