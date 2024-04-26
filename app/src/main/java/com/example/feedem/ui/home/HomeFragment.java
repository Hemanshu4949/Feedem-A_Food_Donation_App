package com.example.feedem.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.feedem.FragmentAdapterfortablayout;
import com.example.feedem.ModelClasses.Ngo_Details;
import com.example.feedem.R;
import com.example.feedem.databinding.FragmentHomeBinding;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Objects;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        FragmentAdapterfortablayout adapter  = new FragmentAdapterfortablayout(this);

        String[] Tabnames = {"About" , "Event" , "Review"};
        binding.viewPager.setAdapter(adapter);
        new TabLayoutMediator(binding.tabLayout , binding.viewPager  , (tab, position) -> tab.setText(Tabnames[position])).attach();
        View view = binding.getRoot();
        if (Ngo_Details.imagecheck())
        {
            binding.NGOImage.setImageBitmap(Ngo_Details.getImage());
        }
        if (Ngo_Details.namecheck())
        {
            binding.Ngoname1.setText(Ngo_Details.getName());
            binding.ReviewValue.setText(Ngo_Details.getReviews());
            binding.campaign.setText(Ngo_Details.getCampaign());
            binding.volunteers.setText(Ngo_Details.getVolunteers());
            binding.Feed.setText(Ngo_Details.getFeeds());

        }
        binding.materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v); // Assuming cookedfood is a View within the fragment
                navController.navigate(R.id.navigation_donate);
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}