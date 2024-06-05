package com.example.kofiserve4.Interfaces;

import android.content.Context;

import com.example.kofiserve4.Tickets.TicketContainer;

public interface OnTicketClick {
    /**
     * Initialize OnClickListener on the TicketContainer
     * @param ticketContainer TicketContainer selected
     * @param position position of TicketContainer in the adapter
     * @param context context for the TicketContainer
     */
    public void onTicketClick(TicketContainer ticketContainer, int position, Context context);
}
