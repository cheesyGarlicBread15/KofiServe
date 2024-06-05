package com.example.kofiserve4.Tickets;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kofiserve4.R;

public class TicketViewHolder extends RecyclerView.ViewHolder {
    TextView ticketTime, ticketCost;

    public TicketViewHolder(@NonNull View itemView) {
        super(itemView);
        ticketTime = itemView.findViewById(R.id.ticket_time);
        ticketCost = itemView.findViewById(R.id.ticket_cost);
    }

    public TextView getTicketTime() {
        return ticketTime;
    }

    public void setTicketTime(TextView ticketTime) {
        this.ticketTime = ticketTime;
    }

    public TextView getTicketCost() {
        return ticketCost;
    }

    public void setTicketCost(TextView ticketCost) {
        this.ticketCost = ticketCost;
    }
}
