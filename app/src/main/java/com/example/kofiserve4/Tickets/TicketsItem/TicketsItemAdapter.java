package com.example.kofiserve4.Tickets.TicketsItem;

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

public class TicketsItemAdapter extends RecyclerView.Adapter<TicketsItemViewHolder> {
    Context context;
    ArrayList<ItemContainer> items;
    OnItemClick itemClickListener;

    public TicketsItemAdapter(Context context, ArrayList<ItemContainer> items, OnItemClick itemClickListener) {
        this.context = context;
        this.items = items;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public TicketsItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final TicketsItemViewHolder ticketsItemVh=  new TicketsItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_container_clickable, parent, false));
        ticketsItemVh.getClickedSave().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Logic for adding item with desired quantity to the ticket
                itemClickListener.onItemClick(items.get(ticketsItemVh.getAdapterPosition()), ticketsItemVh.getAdapterPosition(), context, ticketsItemVh.getQty());
                ticketsItemVh.setQty(1);
                ticketsItemVh.getClickedQuantity().setText(String.valueOf(1));
                ItemContainer selectedItemToOrder = items.get(ticketsItemVh.getAdapterPosition());
                selectedItemToOrder.setClicked(false);
                notifyItemChanged(ticketsItemVh.getAdapterPosition());
            }
        });
        return ticketsItemVh;
    }

    @Override
    public void onBindViewHolder(@NonNull TicketsItemViewHolder holder, int position) {
        ItemContainer selectedItemToOrder = items.get(position);

        /*
        If ItemContainer is clicked, switch to clicked state
        allowing users to select desired quantity
         */
        if (selectedItemToOrder.isClicked()){
            holder.getClickedLayout().setVisibility(View.VISIBLE);
            holder.getOriginalLayout().setVisibility(View.GONE);

            holder.getClickedDelete().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.getQty() == 1){
                        return;
                    } else {
                        holder.lowerQty();
                    }
                    holder.getClickedQuantity().setText(String.valueOf(holder.getQty()));
                }
            });

            holder.getClickedAdd().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.getQty() == 99){
                        return;
                    } else {
                        holder.addQty();
                    }
                    holder.getClickedQuantity().setText(String.valueOf(holder.getQty()));
                }
            });

            holder.getClickedCancel().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.setQty(1);
                    selectedItemToOrder.setClicked(false);
                    notifyItemChanged(position);
                    holder.getClickedQuantity().setText(String.valueOf(1));
                }
            });
        } else {

            /*
            Load the default state
             */
            holder.getOriginalLayout().setVisibility(View.VISIBLE);
            holder.getClickedLayout().setVisibility(View.GONE);

            holder.getNameViewClickable().setText(items.get(position).getItem().getName());
            holder.getCategoryViewClickable().setText(items.get(position).getItem().getCategory());
            holder.getPriceViewClickable().setText(String.valueOf("₱" + items.get(position).getItem().getPrice()));

            holder.getOriginalLayout().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedItemToOrder.setClicked(true);
                    notifyItemChanged(position);
                }
            });

        }
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

}
