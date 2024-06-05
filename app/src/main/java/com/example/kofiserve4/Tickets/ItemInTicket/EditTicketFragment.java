package com.example.kofiserve4.Tickets.ItemInTicket;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.kofiserve4.Fragments.Navigation.SalesFragment;
import com.example.kofiserve4.Fragments.ShowTicketsFragment;
import com.example.kofiserve4.Interfaces.OnItemClick;
import com.example.kofiserve4.Items.ItemContainer;
import com.example.kofiserve4.MainActivity;
import com.example.kofiserve4.R;
import com.example.kofiserve4.Tickets.TicketContainer;

import java.util.ArrayList;


public class EditTicketFragment extends Fragment implements OnItemClick, View.OnClickListener {
    public ArrayList<ItemContainer> itemsInTicket = new ArrayList<>();
    RecyclerView editTicketRecyclerView;
    public ItemInTicketAdapter itemInTicketAdapter;
    Button chargeTicket, voidTicket;
    AlertDialog.Builder builder;
    int selectedTicketPosition;
    public TicketContainer selectedTicket;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_ticket, container, false);

        editTicketRecyclerView = view.findViewById(R.id.edit_ticket_recycler_view);
        chargeTicket = view.findViewById(R.id.charge_ticket);
        voidTicket = view.findViewById(R.id.delete_ticket);

        chargeTicket.setOnClickListener(this::onClick);
        voidTicket.setOnClickListener(this::onClick);
        builder = new AlertDialog.Builder(getContext());

        if (getArguments() != null){
            ordersInit();
        }

        recyclerInit();
        updateTotalCost();

        return view;
    }

    @Override
    public void onItemClick(ItemContainer itemContainer, int position, Context context, int qty) {
//        add onClick listener if have time
    }

    /**
     * Initialize recycler view
     */
    private void recyclerInit(){
        editTicketRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        itemInTicketAdapter = new ItemInTicketAdapter(requireContext(), itemsInTicket, this::onItemClick);
        editTicketRecyclerView.setAdapter(itemInTicketAdapter);
    }

    /**
     *  Initialize items that are ordered (ArrayList)
     */
    private void ordersInit(){
        Bundle bundle = getArguments();
        if (bundle != null){
            selectedTicket = ((TicketContainer) bundle.getParcelable("selected_ticket"));
            itemsInTicket = ((TicketContainer) bundle.getParcelable("selected_ticket")).getTicket().getOrders();
            selectedTicketPosition = bundle.getInt("selected_ticket_position");
        }
    }

    /**
     * Calculate total cost of all items ordered
     * @return total cost of ticket
     */
    private double calculateTotalCost(){
        double totalCost = 0;
        for (ItemContainer item:
             itemsInTicket) {
            totalCost += item.getItem().getTotalPrice();
        }
        return totalCost;
    }

    /**
     * Set the total cost
     */
    private void updateTotalCost(){
        chargeTicket.setText("Charge\n₱" + String.valueOf(calculateTotalCost()));
    }

    /**
     * Initialize OnClickListener on "Void Ticket" and "Charge Ticket"
     * void - remove ticket
     * charge - remove ticket and pass ticket details to SalesFragment
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.charge_ticket){
            builder.setTitle("Charge Ticket")
                    .setMessage("Charge this ticket of ₱" + calculateTotalCost() + "?")
                    .setCancelable(true)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SalesFragment.receiveData(selectedTicket);
                            MainActivity.selectedTicketContainer = selectedTicket;
                            ShowTicketsFragment.tickets.remove(selectedTicketPosition);
                            getParentFragmentManager().popBackStack();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .show();
        } else if (viewId == R.id.delete_ticket) {
            builder.setTitle("Void Ticket")
                    .setMessage("Are you sure you want to void this ticket?")
                    .setCancelable(true)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ShowTicketsFragment.tickets.remove(selectedTicketPosition);
                            getParentFragmentManager().popBackStack();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .show();
        }
    }

}