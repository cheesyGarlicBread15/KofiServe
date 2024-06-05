package com.example.kofiserve4.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kofiserve4.Fragments.Navigation.ItemsFragment;
import com.example.kofiserve4.Fragments.Navigation.TicketsFragment;
import com.example.kofiserve4.Items.Item;
import com.example.kofiserve4.Items.ItemContainer;
import com.example.kofiserve4.MainActivity;
import com.example.kofiserve4.R;
import com.example.kofiserve4.SearchAlgorithms.Sort;

import java.util.ArrayList;


public class AddEditItemFragment extends Fragment {
    EditText itemName, itemPrice, itemCategory, itemDescription;
    Button save;
    String selectedItemName;
    int selectedItemPosition;
    boolean isEdit = false;
    ImageView addEditItemFragmentBack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_edit_item, container, false);
//        Register touches only in this fragment, not pass through it
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE){
                    return false;
                }
                return true;
            }
        });

        itemName = view.findViewById(R.id.item_name);
        itemPrice = view.findViewById(R.id.item_price);
        itemCategory = view.findViewById(R.id.item_category);
        itemDescription = view.findViewById(R.id.item_description);
        save = view.findViewById(R.id.save);
        addEditItemFragmentBack = view.findViewById(R.id.fragment_add_edit_back);

        addEditItemFragmentBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().popBackStack();
            }
        });

        if (getArguments() != null){
            isEdit = true;
            setupSelectedItem();
        } else {
            isEdit = false;
        }

        setUpButton();
        return view;
    }

    /**
     * If save button is clicked, pop the fragment and pass the data to the ItemsFragment,
     * to update changes in the inventory
     * @param itemContainer item to add to the inventory
     */
    private void navigateBackAndPass(ItemContainer itemContainer){
        ItemsFragment itemsFragment = (ItemsFragment) getParentFragmentManager().findFragmentByTag("f" + MainActivity.viewPager2.getCurrentItem());
        if (itemsFragment != null){
            // Pass data to the ItemsFragment
            itemsFragment.receiveData(itemContainer);
        }
        getParentFragmentManager().popBackStack();
        hideKeyboard();
    }

    /**
     * Setup the button logic according to:
     * Edit:
     * Update the ItemContainer on all ArrayList
     *
     * Add:
     * Add the ItemContainer on all Arraylist
     */
    private void setUpButton(){
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = (itemCategory.getText().toString().trim().isEmpty()) ? null : itemCategory.getText().toString();
                String description = (itemDescription.getText().toString().trim().isEmpty()) ? null : itemDescription.getText().toString();
                ItemContainer item = getItemContainer(itemName.getText().toString(), ItemsFragment.items);
                if (getArguments() != null){
//                    Edit
                    if (item != null){
                        Toast.makeText(getActivity(), "Please use another name", Toast.LENGTH_SHORT).show();
                    } else {
                        updateInventory(ItemsFragment.items, category, description);
                        updateInventory(TicketsFragment.items, category, description);
                        updateInventory(MainActivity.items, category, description);

                        Sort.basicSort(ItemsFragment.items, new ItemContainer.NameComparator(), new ItemContainer.NameComparator());
                        Sort.basicSort(TicketsFragment.items, new ItemContainer.NameComparator(), new ItemContainer.NameComparator());

                        ItemsFragment.itemsFragmentAdapter.notifyDataSetChanged();
                        TicketsFragment.ticketsItemAdapter.notifyDataSetChanged();

                        getParentFragmentManager().popBackStack();
                        hideKeyboard();
                    }
                } else {
//                    Add
                    if (itemName.getText().toString().trim().isEmpty() || itemPrice.getText().toString().trim().isEmpty()){
                        Toast.makeText(getActivity(), "Please input name and price", Toast.LENGTH_SHORT).show();
                    } else if (getItemContainer(itemName.getText().toString(), ItemsFragment.items) != null) {
//                        if item name already in inventory
                        Toast.makeText(getActivity(), "Please use another name", Toast.LENGTH_SHORT).show();
                    } else if (Double.parseDouble(itemPrice.getText().toString()) <= 0) {
                        Toast.makeText(getActivity(), "Price must be at least 1", Toast.LENGTH_SHORT).show();
                    } else {
                        ItemContainer itemToAdd = new ItemContainer(new Item(
                                itemName.getText().toString().trim(),
                                category,
                                description,
                                Double.parseDouble(itemPrice.getText().toString()),
                                0
                        ));
                        MainActivity.items.add(itemToAdd);
                        TicketsFragment.items.add(itemToAdd);
                        TicketsFragment.ticketsItemAdapter.notifyDataSetChanged();
                        Sort.basicSort(TicketsFragment.items, new ItemContainer.NameComparator(), new ItemContainer.NameComparator());
                        navigateBackAndPass(itemToAdd);
                    }
                }
            }
        });
    }

    /**
     * If user still has keyboard in display when clicking save button, hide it
     */
    private void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(save.getWindowToken(), 0);
    }

    /**
     * When clicking item in ItemsFragment, setup the item for editing
     */
    private void setupSelectedItem(){
        Bundle selectedItem = getArguments();
        if (selectedItem != null){
            Item item = ((ItemContainer) selectedItem.getParcelable("selected_item")).getItem();
            selectedItemName = item.getName();
            selectedItemPosition = getArguments().getInt("position");

            itemName.setText(item.getName());
            itemPrice.setText(String.valueOf(item.getPrice()));
            itemCategory.setText(item.getCategory());
            itemDescription.setText(item.getDescription());
        }
    }

    /**
     * Check if the name of the item inputted exists in the inventory
     * @param name String to check
     * @param arr ArrayList to check from
     * @return ItemContainer if exists, null otherwise
     */
    public ItemContainer getItemContainer(String name, ArrayList<ItemContainer> arr){
        for (int i = 0; i < arr.size(); i++) {
            if (isEdit && selectedItemPosition == i){
                continue;
            }
            if (arr.get(i).getItem().getName().trim().equalsIgnoreCase(name.trim())){
                return arr.get(i);
            }
        }
        return null;
    }

    /**
     * Update inventory in ItemsFragment
     * @param arr ArrayList to update
     * @param category item category
     * @param description item description
     */
    private void updateInventory(ArrayList<ItemContainer> arr, String category, String description){
        ItemContainer itemToEdit = (getArguments().getParcelable("selected_item"));
        itemToEdit.getItem().setName(itemName.getText().toString());
        itemToEdit.getItem().setPrice(Double.parseDouble(itemPrice.getText().toString()));
        itemToEdit.getItem().setCategory(category);
        itemToEdit.getItem().setDescription(description);
    }

}