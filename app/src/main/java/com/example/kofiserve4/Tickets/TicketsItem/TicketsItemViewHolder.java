package com.example.kofiserve4.Tickets.TicketsItem;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kofiserve4.R;

public class TicketsItemViewHolder extends RecyclerView.ViewHolder {
    TextView nameViewClickable, categoryViewClickable, priceViewClickable, clickedQuantity;
    ImageView clickedDelete, clickedAdd;
    Button clickedSave, clickedCancel;
    RelativeLayout originalLayout, clickedLayout;
    private boolean isClicked = false;
    private int qty = 1;

    public TicketsItemViewHolder(@NonNull View itemView) {
        super(itemView);
        // Default State
        nameViewClickable = itemView.findViewById(R.id.item_name_clickable);
        categoryViewClickable = itemView.findViewById(R.id.item_category_clickable);
        priceViewClickable = itemView.findViewById(R.id.item_price_clickable);
        originalLayout = itemView.findViewById(R.id.item_details_clickable);

        // Clicked State
        clickedQuantity = itemView.findViewById(R.id.quantity);
        clickedDelete = itemView.findViewById(R.id.delete_from_order);
        clickedAdd = itemView.findViewById(R.id.add_to_order);
        clickedSave = itemView.findViewById(R.id.save_order);
        clickedCancel = itemView.findViewById(R.id.cancel_order);
        clickedLayout = itemView.findViewById(R.id.quantity_controls);
    }

    public TextView getNameViewClickable() {
        return nameViewClickable;
    }

    public void setNameViewClickable(TextView nameViewClickable) {
        this.nameViewClickable = nameViewClickable;
    }

    public TextView getCategoryViewClickable() {
        return categoryViewClickable;
    }

    public void setCategoryViewClickable(TextView categoryViewClickable) {
        this.categoryViewClickable = categoryViewClickable;
    }

    public TextView getPriceViewClickable() {
        return priceViewClickable;
    }

    public void setPriceViewClickable(TextView priceViewClickable) {
        this.priceViewClickable = priceViewClickable;
    }

    public TextView getClickedQuantity() {
        return clickedQuantity;
    }

    public void setClickedQuantity(TextView clickedQuantity) {
        this.clickedQuantity = clickedQuantity;
    }

    public ImageView getClickedDelete() {
        return clickedDelete;
    }

    public void setClickedDelete(ImageView clickedDelete) {
        this.clickedDelete = clickedDelete;
    }

    public ImageView getClickedAdd() {
        return clickedAdd;
    }

    public void setClickedAdd(ImageView clickedAdd) {
        this.clickedAdd = clickedAdd;
    }

    public Button getClickedSave() {
        return clickedSave;
    }

    public void setClickedSave(Button clickedSave) {
        this.clickedSave = clickedSave;
    }

    public RelativeLayout getOriginalLayout() {
        return originalLayout;
    }

    public void setOriginalLayout(RelativeLayout originalLayout) {
        this.originalLayout = originalLayout;
    }

    public RelativeLayout getClickedLayout() {
        return clickedLayout;
    }

    public void setClickedLayout(RelativeLayout clickedLayout) {
        this.clickedLayout = clickedLayout;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public Button getClickedCancel() {
        return clickedCancel;
    }

    public void setClickedCancel(Button clickedCancel) {
        this.clickedCancel = clickedCancel;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void lowerQty(){
        qty -= 1;
    }

    public void addQty(){
        qty += 1;
    }
}
