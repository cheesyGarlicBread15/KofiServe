package com.example.kofiserve4.Tickets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kofiserve4.Interfaces.OnTicketClick;
import com.example.kofiserve4.R;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TicketAdapter extends RecyclerView.Adapter<TicketViewHolder> {
    Context context;
    ArrayList<TicketContainer> tickets;
    OnTicketClick onTicketClick;

    public TicketAdapter(Context context, ArrayList<TicketContainer> tickets, OnTicketClick onTicketClick) {
        this.context = context;
        this.tickets = tickets;
        this.onTicketClick = onTicketClick;
    }

    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final TicketViewHolder ticketVh = new TicketViewHolder(LayoutInflater.from(context).inflate(R.layout.ticket_container, parent, false));
        ticketVh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTicketClick.onTicketClick(tickets.get(ticketVh.getAdapterPosition()), ticketVh.getAdapterPosition(), context);
            }
        });

        return ticketVh;
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        holder.getTicketTime().setText(tickets.get(position).getTicket().getTime().format(DateTimeFormatter.ofPattern("h:mm a")));
        holder.getTicketCost().setText(String.valueOf(tickets.get(position).getTicket().getTotalCost()));
    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }
}
