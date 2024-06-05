package com.example.kofiserve4.Sales;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kofiserve4.R;

public class SalesTicketViewHolder extends RecyclerView.ViewHolder {
    ImageView salesCashImage;
    TextView salesTicketTime, salesTicketCost;

    public SalesTicketViewHolder(@NonNull View itemView) {
        super(itemView);
        salesCashImage = itemView.findViewById(R.id.sales_cash_image);
        salesTicketTime = itemView.findViewById(R.id.sales_ticket_time);
        salesTicketCost = itemView.findViewById(R.id.sales_ticket_cost);
    }

    public ImageView getSalesCashImage() {
        return salesCashImage;
    }

    public void setSalesCashImage(ImageView salesCashImage) {
        this.salesCashImage = salesCashImage;
    }

    public TextView getSalesTicketTime() {
        return salesTicketTime;
    }

    public void setSalesTicketTime(TextView salesTicketTime) {
        this.salesTicketTime = salesTicketTime;
    }

    public TextView getSalesTicketCost() {
        return salesTicketCost;
    }

    public void setSalesTicketCost(TextView salesTicketCost) {
        this.salesTicketCost = salesTicketCost;
    }
}
