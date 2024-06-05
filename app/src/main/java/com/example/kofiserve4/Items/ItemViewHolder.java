package com.example.kofiserve4.Items;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kofiserve4.R;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    TextView nameView, categoryView, priceView;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        nameView = itemView.findViewById(R.id.item_name);
        categoryView = itemView.findViewById(R.id.item_category);
        priceView = itemView.findViewById(R.id.item_price);
    }


    public TextView getNameView() {
        return nameView;
    }

    public void setNameView(TextView nameView) {
        this.nameView = nameView;
    }

    public TextView getCategoryView() {
        return categoryView;
    }

    public void setCategoryView(TextView categoryView) {
        this.categoryView = categoryView;
    }

    public TextView getPriceView() {
        return priceView;
    }

    public void setPriceView(TextView priceView) {
        this.priceView = priceView;
    }

}
