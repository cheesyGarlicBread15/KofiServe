package com.example.kofiserve4.Items;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.time.LocalDateTime;

public class Item implements Parcelable {

    private String name, category, description;
    private double price;
    private final LocalDateTime timeCreated;
    private int quantity;

    /**
     * Item constructor
     * @param name item name, must not be null
     * @param category item category, can be null
     * @param description item description, can be null
     * @param price item price, must not be null
     * @param quantity item quantity
     */
    public Item(String name, String category, String description, double price, int quantity) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.timeCreated = LocalDateTime.now();
        this.quantity = quantity;
    }

    protected Item(Parcel in) {
        name = in.readString();
        category = in.readString();
        description = in.readString();
        price = in.readDouble();
        timeCreated = LocalDateTime.now();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice(){
        return quantity * price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(category);
        dest.writeString(description);
        dest.writeDouble(price);
    }
}
