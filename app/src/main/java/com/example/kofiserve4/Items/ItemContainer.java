package com.example.kofiserve4.Items;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.kofiserve4.Interfaces.ComparatorAndValidator;

public class ItemContainer implements Parcelable {
    Item item;
    private boolean isClicked;

    /**
     * ItemContainer constructor
     * @param item Item object
     */
    public ItemContainer(Item item) {
        this.item = item;
    }

    protected ItemContainer(Parcel in) {
        item = in.readParcelable(Item.class.getClassLoader());
    }

    public static final Creator<ItemContainer> CREATOR = new Creator<ItemContainer>() {
        @Override
        public ItemContainer createFromParcel(Parcel in) {
            return new ItemContainer(in);
        }

        @Override
        public ItemContainer[] newArray(int size) {
            return new ItemContainer[size];
        }
    };

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeParcelable(item, flags);
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    /**
     * Inner comparator classes, utilized when sorting objects
     * NameComparator - sort by name
     * PriceComparator - sort by price
     * CategoryComparator - sort by category
     *
     */
    public static class NameComparator implements ComparatorAndValidator<ItemContainer> {
        @Override
        public int compare(ItemContainer o1, ItemContainer o2) {
            return o1.getItem().getName().trim().toLowerCase().compareTo(o2.getItem().getName().trim().toLowerCase());
        }
        @Override
        public ItemContainer validate(ItemContainer o) {
            return (o.getItem().getName() != null) ? o : null;
        }
    }

    public static class PriceComparator implements ComparatorAndValidator<ItemContainer>{
        @Override
        public int compare(ItemContainer o1, ItemContainer o2) {
            if (o1.getItem().getPrice() > o2.getItem().getPrice()) {
                return 1;
            } else if (o1.getItem().getPrice() < o2.getItem().getPrice()) {
                return -1;
            }
            return 0;
        }
        @Override
        public ItemContainer validate(ItemContainer o) {
            return (o.getItem().getPrice() != 0) ? o : null;
        }
    }

    public static class CategoryComparator implements ComparatorAndValidator<ItemContainer> {
        @Override
        public int compare(ItemContainer o1, ItemContainer o2) {
            return o1.getItem().getCategory().trim().toLowerCase().compareTo(o2.getItem().getCategory().trim().toLowerCase());
        }
        @Override
        public ItemContainer validate(ItemContainer o) {
            return (o.getItem().getCategory() != null) ? o : null;
        }
    }

}
