package com.example.kofiserve4.Tickets.ItemInTicket;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kofiserve4.R;

public class ItemInTicketViewHolder extends RecyclerView.ViewHolder {
    TextView itemInTicketName, itemInTicketPrice, itemInTicketQuantity;

    public ItemInTicketViewHolder(@NonNull View itemView) {
        super(itemView);
        itemInTicketName = itemView.findViewById(R.id.item_in_ticket_name);
        itemInTicketPrice = itemView.findViewById(R.id.item_in_ticket_price);
        itemInTicketQuantity = itemView.findViewById(R.id.item_in_ticket_quantity);
    }

    public TextView getItemInTicketName() {
        return itemInTicketName;
    }

    public void setItemInTicketName(TextView itemInTicketName) {
        this.itemInTicketName = itemInTicketName;
    }

    public TextView getItemInTicketPrice() {
        return itemInTicketPrice;
    }

    public void setItemInTicketPrice(TextView itemInTicketPrice) {
        this.itemInTicketPrice = itemInTicketPrice;
    }

    public TextView getItemInTicketQuantity() {
        return itemInTicketQuantity;
    }

    public void setItemInTicketQuantity(TextView itemInTicketQuantity) {
        this.itemInTicketQuantity = itemInTicketQuantity;
    }
}
