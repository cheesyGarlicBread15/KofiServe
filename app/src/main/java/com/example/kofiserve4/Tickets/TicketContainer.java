package com.example.kofiserve4.Tickets;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.kofiserve4.Interfaces.ComparatorAndValidator;

public class TicketContainer implements Parcelable {
    private Ticket ticket;

    public TicketContainer(Ticket ticket) {
        this.ticket = ticket;
    }

    protected TicketContainer(Parcel in) {
    }

    public static final Creator<TicketContainer> CREATOR = new Creator<TicketContainer>() {
        @Override
        public TicketContainer createFromParcel(Parcel in) {
            return new TicketContainer(in);
        }

        @Override
        public TicketContainer[] newArray(int size) {
            return new TicketContainer[size];
        }
    };

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
    }

    /**
     * Inner Comparator classes
     * time - sort by time
     * price - sort by total cost
     */
    public static class TimeCreatedComparator implements ComparatorAndValidator<TicketContainer>{

        @Override
        public int compare(TicketContainer o1, TicketContainer o2) {
            return o1.getTicket().getTime().compareTo(o2.getTicket().getTime());
        }

        @Override
        public TicketContainer validate(TicketContainer o) {
            return (o.getTicket().getTime() != null) ? o : null;
        }
    }

    public static class PriceComparator implements ComparatorAndValidator<TicketContainer>{

        @Override
        public int compare(TicketContainer o1, TicketContainer o2) {
            if (o1.getTicket().getTotalCost() > o2.getTicket().getTotalCost()){
                return 1;
            } else if (o1.getTicket().getTotalCost() < o2.getTicket().getTotalCost()) {
                return -1;
            }
            return 0;
        }

        @Override
        public TicketContainer validate(TicketContainer o) {
            return (o.getTicket().getTotalCost() != 0) ? o : null;
        }
    }
}
