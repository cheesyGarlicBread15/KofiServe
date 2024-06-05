package com.example.kofiserve4.Tickets;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.kofiserve4.Items.ItemContainer;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Ticket implements Parcelable {
    private ArrayList<ItemContainer> orders;
    private double totalCost;
    private LocalDateTime time;

    /**
     * Ticket constructor
     * @param orders ArrayList of selected ItemContainers that are ordered
     * @param totalCost Total cost of all items ordered
     */
    public Ticket(ArrayList<ItemContainer> orders, double totalCost) {
        this.orders = orders;
        this.totalCost = totalCost;
        this.time = LocalDateTime.now();
    }

    protected Ticket(Parcel in) {
        orders = in.createTypedArrayList(ItemContainer.CREATOR);
        totalCost = in.readDouble();
    }

    public static final Creator<Ticket> CREATOR = new Creator<Ticket>() {
        @Override
        public Ticket createFromParcel(Parcel in) {
            return new Ticket(in);
        }

        @Override
        public Ticket[] newArray(int size) {
            return new Ticket[size];
        }
    };

    public ArrayList<ItemContainer> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<ItemContainer> orders) {
        this.orders = orders;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void addItem(ItemContainer item){
        orders.add(item);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeTypedList(orders);
        dest.writeDouble(totalCost);
    }
}
