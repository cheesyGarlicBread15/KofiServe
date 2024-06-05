package com.example.kofiserve4;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.kofiserve4.Items.Item;
import com.example.kofiserve4.Items.ItemContainer;
import com.example.kofiserve4.Tickets.TicketContainer;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<ItemContainer> items = new ArrayList<>();
    public static ViewPager2 viewPager2;
    ViewPagerAdapter viewPagerAdapter;
    BottomNavigationView bottomNavigationView;
    public static TicketContainer selectedTicketContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bottomNavigationView = findViewById(R.id.bottom_nav);
        viewPager2 = findViewById(R.id.view_pager);

        dummyData();
        setupNavigation();

    }

    /**
     * Setup navigation logic and additional viewpager and fragment configurations
     */
    private void setupNavigation(){
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(viewPagerAdapter);
//        Disable swipe
        viewPager2.setUserInputEnabled(false);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.nav_tickets){
                    viewPager2.setCurrentItem(0);
                } else if (itemId == R.id.nav_items) {
                    viewPager2.setCurrentItem(1);
                } else if (itemId == R.id.nav_sales) {
                    viewPager2.setCurrentItem(2);
                }
                return true;
            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.nav_tickets).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.nav_items).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.nav_sales).setChecked(true);
                        break;
                }
                super.onPageSelected(position);
            }
        });
    }

    /**
     * Initialize dummy data
     */
    private void dummyData(){
//            20 items
        items.add(new ItemContainer(new Item("Espresso", "Coffee", "A concentrated coffee beverage brewed by forcing hot water under pressure through finely-ground coffee beans", 90.00, 0)));
        items.add(new ItemContainer(new Item("Cappuccino", null, "An espresso-based coffee drink that is typically prepared with steamed milk foam", 120.00, 0)));
        items.add(new ItemContainer(new Item("Latte", "Coffee", "A coffee drink made with espresso and steamed milk", 110.00, 0)));
        items.add(new ItemContainer(new Item("Mocha", "Coffee", "A chocolate-flavored variant of a latte, often topped with whipped cream", 130.00, 0)));
        items.add(new ItemContainer(new Item("Americano", null, "A coffee drink prepared by diluting an espresso with hot water", 100.00, 0)));
        items.add(new ItemContainer(new Item("Macchiato", null, null, 100.00, 0)));
        items.add(new ItemContainer(new Item("Flat White", "Coffee", "A coffee drink originating from Australia, made with espresso and microfoam", 120.00, 0)));
        items.add(new ItemContainer(new Item("Café au Lait", "Coffee", "A French coffee drink consisting of strong brewed coffee and scalded milk", 110.00, 0)));
        items.add(new ItemContainer(new Item("Iced Coffee", null, "Chilled coffee served over ice, often sweetened and/or with milk added", 120.00, 0)));
        items.add(new ItemContainer(new Item("Chai Latte", "Tea", "A spiced tea concentrate mixed with steamed milk", 140.00, 0)));
        items.add(new ItemContainer(new Item("Hot Chocolate", "Chocolate", "A warm beverage made from melted chocolate or cocoa powder, often with milk and sugar", 120.00, 0)));
        items.add(new ItemContainer(new Item("Tea", "Tea", "A hot beverage made by steeping dried tea leaves or herbs in hot water", 80.00, 0)));
        items.add(new ItemContainer(new Item("Croissant", "Pastry", "A buttery, flaky pastry originating from France", 70.00, 0)));
        items.add(new ItemContainer(new Item("Bagel", "Bakery", null, 60.00, 0)));
        items.add(new ItemContainer(new Item("Muffin", "Pastry", null, 80.00, 0)));
        items.add(new ItemContainer(new Item("Sandwich", null, "A food typically consisting of vegetables, cheese, and/or meat served between slices of bread", 150.00, 0)));
        items.add(new ItemContainer(new Item("Cookie", "Pastry", "A sweet baked food typically containing flour, sugar, and eggs, often with added flavors such as chocolate chips or nuts", 50.00, 0)));
        items.add(new ItemContainer(new Item("Scone", "Pastry", null, 80.00, 0)));
        items.add(new ItemContainer(new Item("Brownie", null, "A square, baked dessert, usually chocolate, characterized by its dense and fudgy texture", 70.00, 0)));
        items.add(new ItemContainer(new Item("Fruit Salad", "Food", "A dish consisting of various fruits, often served as a dessert", 100.00, 0)));

    }


}