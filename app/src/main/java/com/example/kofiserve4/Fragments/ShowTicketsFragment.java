package com.example.kofiserve4.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.kofiserve4.Interfaces.OnTicketClick;
import com.example.kofiserve4.R;
import com.example.kofiserve4.SearchAlgorithms.Sort;
import com.example.kofiserve4.Tickets.ItemInTicket.EditTicketFragment;
import com.example.kofiserve4.Tickets.TicketAdapter;
import com.example.kofiserve4.Tickets.TicketContainer;

import java.util.ArrayList;


public class ShowTicketsFragment extends Fragment implements OnTicketClick, View.OnClickListener {
    public static ArrayList<TicketContainer> tickets = new ArrayList<>();
    RecyclerView ticketsFragmentRecyclerView;
    public static TicketAdapter showTicketsFragmentAdapter;
    TextView emptyOrders;
    ImageView ticketsFragmentSort, showTicketsFragmentBack;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_tickets, container, false);
        emptyOrders = view.findViewById(R.id.show_tickets_empty);
        ticketsFragmentSort = view.findViewById(R.id.items_show_tickets_sort);
        showTicketsFragmentBack = view.findViewById(R.id.fragment_show_tickets_back);


        showTicketsFragmentBack.setOnClickListener(this::onClick);
        ticketsFragmentSort.setOnClickListener(this::onClick);


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

        ticketsFragmentRecyclerView = view.findViewById(R.id.show_tickets_fragment_recycler_view);

        recyclerInit();

        return view;
    }

    /**
     * Initialize ticket OnClickListener
     * @param ticketContainer clicked TicketContainer
     * @param position position of TicketContainer in adapter
     * @param context context for TicketContainer
     */
    @Override
    public void onTicketClick(TicketContainer ticketContainer, int position, Context context) {
        EditTicketFragment editTicketFragment = new EditTicketFragment();
        Bundle data = new Bundle();
        data.putParcelable("selected_ticket", ticketContainer);
        data.putInt("selected_ticket_position", position);
        editTicketFragment.setArguments(data);
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, editTicketFragment, "EditTicketFragment")
                .addToBackStack(null)
                .commit();
    }

    /**
     * Initialize recycler view
     */
    private void recyclerInit(){
        if (tickets.isEmpty()){
            emptyOrders.setVisibility(View.VISIBLE);
        } else {
            emptyOrders.setVisibility(View.GONE);
            ticketsFragmentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            showTicketsFragmentAdapter = new TicketAdapter(requireContext(), tickets, this);
            ticketsFragmentRecyclerView.setAdapter(showTicketsFragmentAdapter);
        }

    }

    /**
     * Sort tickets via time or cost
     * @param v view of the widget holding the onClickListener
     */
    private void showSortOptions(View v){
        PopupMenu popupMenu = new PopupMenu(getActivity(), v);
        popupMenu.getMenuInflater().inflate(R.menu.show_tickets_fragment_sort, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (tickets.isEmpty()){
                    return false;
                } else if (item.getItemId() == R.id.show_tickets_sort_by_price){
                    Sort.basicSort(tickets, new TicketContainer.PriceComparator(), new TicketContainer.TimeCreatedComparator());
                    showTicketsFragmentAdapter.notifyDataSetChanged();
                } else if (item.getItemId() == R.id.show_tickets_sort_by_time) {
                    Sort.basicSort(tickets, new TicketContainer.TimeCreatedComparator(), new TicketContainer.TimeCreatedComparator());
                    showTicketsFragmentAdapter.notifyDataSetChanged();
                }
                return true;
            }
        });
        popupMenu.show();
    }

    /**
     * Initialize OnClickListener to the back and sort button
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.items_show_tickets_sort){
            showSortOptions(v);
        } else if (v.getId() == R.id.fragment_show_tickets_back){
            getParentFragmentManager().popBackStack();
        }
    }
}