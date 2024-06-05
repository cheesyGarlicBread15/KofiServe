package com.example.kofiserve4.Fragments.Navigation;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.kofiserve4.Fragments.AddEditItemFragment;
import com.example.kofiserve4.Interfaces.OnItemClick;
import com.example.kofiserve4.Items.ItemAdapter;
import com.example.kofiserve4.Items.ItemContainer;
import com.example.kofiserve4.MainActivity;
import com.example.kofiserve4.R;
import com.example.kofiserve4.SearchAlgorithms.Sort;

import java.util.ArrayList;


public class ItemsFragment extends Fragment implements OnItemClick {
    public static ArrayList<ItemContainer> items = new ArrayList<>(MainActivity.items);
    RecyclerView itemsFragmentRecyclerView;
    public static ItemAdapter itemsFragmentAdapter;
    ImageButton addItem;
    ImageView sort;
    public static TextView empty;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_items, container, false);

        itemsFragmentRecyclerView = view.findViewById(R.id.items_fragment_recycler_view);
        addItem = view.findViewById(R.id.addItem);
        empty = view.findViewById(R.id.empty);
        sort = view.findViewById(R.id.items_fragment_sort);

        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSortOptions(v);
            }
        });

        addItemBtn();
        recyclerInit();

        return view;
    }

    /**
     * Method to initialize the recyclerview
     */
    private void recyclerInit(){
        if (items.size() != 0){
            Sort.basicSort(items, new ItemContainer.NameComparator(), new ItemContainer.NameComparator());
        }
        itemsFragmentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        itemsFragmentAdapter = new ItemAdapter(requireContext(), items, this);
        itemsFragmentRecyclerView.setAdapter(itemsFragmentAdapter);

    }

    /**
     * Method to set the onclick listener for the addItemBtn, going to a new Fragment to add or edit
     * an item to the inventory
     */
    private void addItemBtn(){
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(android.R.id.content, new AddEditItemFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    /**
     * This method initializes an onclick listener for the itemContainer in the onCreateViewHolder
     * @param itemContainer the itemContainer clicked
     * @param position position of the itemContainer in the adapter
     * @param context context of the itemContainer
     * @param qty quantity of the itemContainer
     */
    @Override
    public void onItemClick(ItemContainer itemContainer, int position, Context context, int qty) {
        Bundle data = new Bundle();
        data.putParcelable("selected_item", itemContainer);
        data.putInt("position", position);
        AddEditItemFragment addEditItemFragment = new AddEditItemFragment();
        addEditItemFragment.setArguments(data);
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, addEditItemFragment)
                .addToBackStack(null)
                .commit();
    }

    /**
     * Receive data from the AddEditItemFragment
     * @param itemContainer itemContainer to add to inventory
     */
    public void receiveData(ItemContainer itemContainer){
        items.add(itemContainer);
        Sort.basicSort(items, new ItemContainer.NameComparator(), new ItemContainer.NameComparator());
        itemsFragmentAdapter.notifyDataSetChanged();
    }

    /**
     * Sort inventory via category, namne or price
     * @param v view of the widget holding the onClickListener
     */
    private void showSortOptions(View v){
        PopupMenu popupMenu = new PopupMenu(getActivity(), v);
        popupMenu.getMenuInflater().inflate(R.menu.items_fragment_sort, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.sort_by_category){
                    Sort.basicSort(items, new ItemContainer.CategoryComparator(), new ItemContainer.NameComparator());
                    itemsFragmentAdapter.notifyDataSetChanged();
                } else if (item.getItemId() == R.id.sort_by_name) {
                    Sort.basicSort(items, new ItemContainer.NameComparator(), new ItemContainer.NameComparator());
                    itemsFragmentAdapter.notifyDataSetChanged();
                } else if (item.getItemId() == R.id.sort_by_price) {
                    Sort.basicSort(items, new ItemContainer.PriceComparator(), new ItemContainer.NameComparator());
                    itemsFragmentAdapter.notifyDataSetChanged();
                }
                return true;
            }
        });
        popupMenu.show();
    }
}