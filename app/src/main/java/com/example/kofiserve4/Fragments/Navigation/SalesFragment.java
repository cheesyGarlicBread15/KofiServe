package com.example.kofiserve4.Fragments.Navigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.kofiserve4.R;
import com.example.kofiserve4.Sales.SalesTicketAdapter;
import com.example.kofiserve4.SearchAlgorithms.Sort;
import com.example.kofiserve4.Tickets.TicketContainer;

import java.util.ArrayList;


public class SalesFragment extends Fragment {
    public static ArrayList<TicketContainer> finishedTickets = new ArrayList<>();
    RecyclerView salesFragmentRecyclerView;
    public static SalesTicketAdapter salesTicketAdapter;
    TextView noSales;
    ImageView salesFragmentSort;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sales, container, false);

        salesFragmentRecyclerView = view.findViewById(R.id.sales_fragment_recycler_view);
        noSales = view.findViewById(R.id.no_sales);
        salesFragmentSort = view.findViewById(R.id.sales_fragment_sort);

        salesFragmentSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSortOptions(v);
            }
        });


        recyclerInit();


        return view;
    }

    /**
     * Initialize recycler view of finished and charged tickets
     */
    private void recyclerInit(){
        salesFragmentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        salesTicketAdapter = new SalesTicketAdapter(requireContext(), finishedTickets);
        salesFragmentRecyclerView.setAdapter(salesTicketAdapter);
    }

    /**
     * Receive data from the EditTicketFragment
     * @param ticketContainer ticketContainer that is charged
     */
    public static void receiveData(TicketContainer ticketContainer){
        finishedTickets.add(ticketContainer);
        if (salesTicketAdapter != null){
            salesTicketAdapter.notifyDataSetChanged();
        }
    }

    /**
     * Sort tickets via time or total cost
     * @param v view of the widget holding the onClickListener
     */
    private void showSortOptions(View v){
        PopupMenu popupMenu = new PopupMenu(getActivity(), v);
        popupMenu.getMenuInflater().inflate(R.menu.show_tickets_fragment_sort, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (finishedTickets.isEmpty()){
                    return false;
                } else if (item.getItemId() == R.id.show_tickets_sort_by_price){
                    Sort.basicSort(finishedTickets, new TicketContainer.PriceComparator(), new TicketContainer.TimeCreatedComparator());
                    salesTicketAdapter.notifyDataSetChanged();
                } else if (item.getItemId() == R.id.show_tickets_sort_by_time) {
                    Sort.basicSort(finishedTickets, new TicketContainer.TimeCreatedComparator(), new TicketContainer.TimeCreatedComparator());
                    salesTicketAdapter.notifyDataSetChanged();
                }
                return true;
            }
        });
        popupMenu.show();
    }
}