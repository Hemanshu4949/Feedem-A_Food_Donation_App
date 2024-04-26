package com.example.feedem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.feedem.HomeFragement.About;
import com.example.feedem.HomeFragement.Event;
import com.example.feedem.HomeFragement.Reviews;

public class FragmentAdapterfortablayout extends FragmentStateAdapter {
    public FragmentAdapterfortablayout(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return About.newInstance();
            case 1:
                return Event.newInstance();
            case 2:
                return Reviews.newInstance();
            default:
                return null; // Handle this case if needed
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
