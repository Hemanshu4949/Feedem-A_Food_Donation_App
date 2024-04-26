package com.example.feedem.ui.donate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.feedem.ModelClasses.Donation_Data;
import com.example.feedem.R;
import com.example.feedem.databinding.FragmentDonateBinding;

public class DonateFragment extends Fragment {

    private FragmentDonateBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DonateViewModel donateViewModel =
                new ViewModelProvider(this).get(DonateViewModel.class);

        binding = FragmentDonateBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.cookedfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
//                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.fooddonationcategories , new HomeFragment());
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
                Donation_Data.setFood_Categorie("Cooked_Food");
                NavController navController = Navigation.findNavController(v); // Assuming cookedfood is a View within the fragment
                navController.navigate(R.id.fooddonationcategories); // Replace with the actual action ID for navigating to HomeFragment
            }
        });
        binding.rawfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
//                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.fooddonationcategories , new HomeFragment());
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
                Donation_Data.setFood_Categorie("Raw_Food");
                NavController navController = Navigation.findNavController(v); // Assuming cookedfood is a View within the fragment
                navController.navigate(R.id.fooddonationcategories); // Replace with the actual action ID for navigating to HomeFragment
            }
        });
        binding.packedfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
//                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.fooddonationcategories , new HomeFragment());
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
                Donation_Data.setFood_Categorie("Packed_Food");
                NavController navController = Navigation.findNavController(v); // Assuming cookedfood is a View within the fragment
                navController.navigate(R.id.fooddonationcategories); // Replace with the actual action ID for navigating to HomeFragment
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}