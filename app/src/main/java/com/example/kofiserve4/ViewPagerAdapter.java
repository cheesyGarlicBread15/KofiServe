package com.example.kofiserve4;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.kofiserve4.Fragments.Navigation.ItemsFragment;
import com.example.kofiserve4.Fragments.Navigation.SalesFragment;
import com.example.kofiserve4.Fragments.Navigation.TicketsFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        /*
        Fragment ids are accessed by "f#"
        f0 = TicketsFragment
        f1 = ItemsFragment
        f2 = SalesFragment
         */
        switch (position){
            case 0: return new TicketsFragment();
            case 1: return new ItemsFragment();
            case 2: return new SalesFragment();
            default: return new TicketsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
