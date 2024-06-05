package com.example.kofiserve4.Sales;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kofiserve4.R;
import com.example.kofiserve4.Tickets.TicketContainer;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SalesTicketAdapter extends RecyclerView.Adapter<SalesTicketViewHolder> {
    Context context;
    ArrayList<TicketContainer> salesTickets;

    public SalesTicketAdapter(Context context, ArrayList<TicketContainer> salesTickets) {
        this.context = context;
        this.salesTickets = salesTickets;
    }

    @NonNull
    @Override
    public SalesTicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SalesTicketViewHolder(LayoutInflater.from(context).inflate(R.layout.ticket_sales_container, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SalesTicketViewHolder holder, int position) {
        holder.getSalesCashImage().setImageResource(R.drawable.cash_multiple);
        holder.getSalesTicketTime().setText(String.valueOf(salesTickets.get(position).getTicket().getTime().format(DateTimeFormatter.ofPattern("h:mm a"))));
        holder.getSalesTicketCost().setText(String.valueOf(salesTickets.get(position).getTicket().getTotalCost()));
    }

    @Override
    public int getItemCount() {
        return salesTickets.size();
    }
}
