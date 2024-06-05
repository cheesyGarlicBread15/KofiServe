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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.kofiserve4.Fragments.ShowTicketsFragment;
import com.example.kofiserve4.Interfaces.OnItemClick;
import com.example.kofiserve4.Items.ItemContainer;
import com.example.kofiserve4.MainActivity;
import com.example.kofiserve4.R;
import com.example.kofiserve4.SearchAlgorithms.Sort;
import com.example.kofiserve4.Tickets.Ticket;
import com.example.kofiserve4.Tickets.TicketContainer;
import com.example.kofiserve4.Tickets.TicketsItem.TicketsItemAdapter;

import java.util.ArrayList;


public class TicketsFragment extends Fragment implements OnItemClick, View.OnClickListener {
    public static ArrayList<ItemContainer> items = new ArrayList<>(MainActivity.items);
    public ArrayList<ItemContainer> currentTicketOrders = new ArrayList<>();
    RecyclerView ticketsItemsFragmentRecyclerView;
    public static TicketsItemAdapter ticketsItemAdapter;
    Button showTickets, addTicket;
    ImageView ticketsFragmentSort;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tickets, container, false);

        ticketsItemsFragmentRecyclerView = view.findViewById(R.id.tickets_fragment_recycler_view);
        showTickets = view.findViewById(R.id.show_tickets);
        addTicket = view.findViewById(R.id.add_ticket);
        ticketsFragmentSort = view.findViewById(R.id.tickets_fragment_sort);

        ticketsFragmentSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSortOptions(v);
            }
        });

        showTickets.setOnClickListener(this::onClick);
        addTicket.setOnClickListener(this::onClick);

        recyclerInit();

        return view;
    }

    /**
     * This method initializes an onclick listener for the itemContainer, allowing users to add
     * items to their order
     * @param itemContainer the itemContainer clicked
     * @param position position of the itemContainer in the adapter
     * @param context context of the itemContainer
     * @param qty quantity desired by the user
     */
    @Override
    public void onItemClick(ItemContainer itemContainer, int position, Context context, int qty) {
        itemContainer.getItem().setQuantity(qty);
        currentTicketOrders.add(itemContainer);
        Toast.makeText(context, "Added: " + itemContainer.getItem().getName(), Toast.LENGTH_SHORT).show();
    }

    /**
     * Initializes recycler view
     */
    private void recyclerInit(){
        if (items.size() != 0){
            Sort.basicSort(items, new ItemContainer.NameComparator(), new ItemContainer.NameComparator());
        }
        ticketsItemsFragmentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ticketsItemAdapter = new TicketsItemAdapter(requireContext(), items, this);
        ticketsItemsFragmentRecyclerView.setAdapter(ticketsItemAdapter);
    }

    /**
     * Set an onClickListener to buttons "Add Ticket" and "Show Tickets"
     * @param v view of the widget that is clicked
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.show_tickets){
            ShowTicketsFragment showTicketsFragment = new ShowTicketsFragment();
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(android.R.id.content, showTicketsFragment)
                    .addToBackStack(null)
                    .commit();
        } else if (id == R.id.add_ticket) {
            if (currentTicketOrders.isEmpty()){
                Toast.makeText(getActivity(), "No Orders Placed!", Toast.LENGTH_SHORT).show();
            } else {
                ShowTicketsFragment.tickets.add(new TicketContainer(new Ticket(new ArrayList<>(currentTicketOrders), calculateTotal())));
//                After adding ticket, clear it
                currentTicketOrders.clear();
                if (ShowTicketsFragment.showTicketsFragmentAdapter != null){
                    Sort.basicSort(ShowTicketsFragment.tickets, new TicketContainer.TimeCreatedComparator(), new TicketContainer.TimeCreatedComparator());
                    ShowTicketsFragment.showTicketsFragmentAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    /**
     * Iterate over the ArrayList currentTicketOrders and calculate every item's total cost
     * by multiplying quantity and price
     * @return total cost of ticket
     */
    private double calculateTotal(){
        double total = 0;
        for (ItemContainer item:
             currentTicketOrders) {
            total += item.getItem().getTotalPrice();
        }
        return total;
    }

    /**
     * Sort inventory via category, name or price
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
                    ticketsItemAdapter.notifyDataSetChanged();
                } else if (item.getItemId() == R.id.sort_by_name) {
                    Sort.basicSort(items, new ItemContainer.NameComparator(), new ItemContainer.NameComparator());
                    ticketsItemAdapter.notifyDataSetChanged();
                } else if (item.getItemId() == R.id.sort_by_price) {
                    Sort.basicSort(items, new ItemContainer.PriceComparator(), new ItemContainer.NameComparator());
                    ticketsItemAdapter.notifyDataSetChanged();
                }
                return true;
            }
        });
        popupMenu.show();
    }
}