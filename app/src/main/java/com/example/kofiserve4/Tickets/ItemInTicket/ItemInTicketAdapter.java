package com.example.kofiserve4.Tickets.ItemInTicket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kofiserve4.Interfaces.OnItemClick;
import com.example.kofiserve4.Items.ItemContainer;
import com.example.kofiserve4.R;

import java.util.ArrayList;

public class ItemInTicketAdapter extends RecyclerView.Adapter<ItemInTicketViewHolder> {
    Context context;
    ArrayList<ItemContainer> itemsInTicket;
    OnItemClick onItemClick;

    public ItemInTicketAdapter(Context context, ArrayList<ItemContainer> itemsInTicket, OnItemClick onItemClick) {
        this.context = context;
        this.itemsInTicket = itemsInTicket;
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public ItemInTicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final ItemInTicketViewHolder itemInTicketVh = new ItemInTicketViewHolder(LayoutInflater.from(context).inflate(R.layout.item_in_ticket_container, parent, false));
        itemInTicketVh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(itemsInTicket.get(itemInTicketVh.getAdapterPosition()), itemInTicketVh.getAdapterPosition(), context, 0);
            }
        });
        return itemInTicketVh;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemInTicketViewHolder holder, int position) {
        holder.itemInTicketName.setText(itemsInTicket.get(position).getItem().getName());
        holder.itemInTicketPrice.setText("₱" + String.valueOf(itemsInTicket.get(position).getItem().getTotalPrice()));
        holder.itemInTicketQuantity.setText("x  " + String.valueOf(itemsInTicket.get(position).getItem().getQuantity()));
    }

    @Override
    public int getItemCount() {
        return itemsInTicket.size();
    }
}
