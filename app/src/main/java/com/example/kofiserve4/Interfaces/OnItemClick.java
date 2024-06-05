package com.example.kofiserve4.Interfaces;

import android.content.Context;

import com.example.kofiserve4.Items.ItemContainer;

public interface OnItemClick {
    /**
     * Initialize OnClickListener on the ItemContainer
     * @param itemContainer ItemContainer selected
     * @param position position of ItemContainer in the adapter
     * @param context context for ItemContainer
     * @param qty quantity for the ItemContainer, to calculate total cost
     */
    void onItemClick(ItemContainer itemContainer, int position, Context context, int qty);
}
