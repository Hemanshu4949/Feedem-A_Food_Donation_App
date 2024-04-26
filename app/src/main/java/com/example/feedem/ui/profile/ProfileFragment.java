package com.example.feedem.ui.profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.feedem.MainActivity;
import com.example.feedem.ModelClasses.Donation_Data;
import com.example.feedem.ModelClasses.Profile_Data;
import com.example.feedem.R;
import com.example.feedem.databinding.FragmentProfileBinding;
import com.example.feedem.ui.Credentials_chacking.credential_removal;
import com.example.feedem.ui.Credentials_chacking.login;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private FirebaseAuth mAuth;
    ImageView profile_image;
    Button editBtn, dhistoryBtn , helpBtn , logoutBtn ;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        mAuth = FirebaseAuth.getInstance();
        editBtn = view.findViewById(R.id.profileEditBtn);
        dhistoryBtn = view.findViewById(R.id.DonationHistory);
        helpBtn = view.findViewById(R.id.helpBtn);
        logoutBtn = view.findViewById(R.id.logOutProfileBtn);
        profile_image = view.findViewById(R.id.imageView);

        if (Profile_Data.imagecheck())
        {
            profile_image.setImageBitmap(Profile_Data.getImage());
        }
        if (Profile_Data.namecheck())
        {
            binding.textView7.setText(Profile_Data.getName());
        }

        dhistoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v); // Assuming cookedfood is a View within the fragment
                navController.navigate(R.id.DonationHistory);
            }
        });
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
//                Intent intent = new Intent(getContext(), Edit_Profile.class);
//                startActivity(intent);
                NavController navController = Navigation.findNavController(v); // Assuming cookedfood is a View within the fragment
                navController.navigate(R.id.edit_profile); // Replace with the actual action ID for navigating to HomeFragment
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                try {

                    mAuth.signOut();
                    Intent intent = new Intent(getActivity(), credential_removal.class);
                    startActivity(intent);

                }
                catch(Exception e)
                {
                    Log.d("signouterror" , "hi");
                }

            }
        });


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


//    private byte[] retrieveImage(String imagePath)
//    {
//        final byte[] downloadedBytes[] = new byte[1][];
//        StorageReference storageRef;
//        FirebaseStorage storage = FirebaseStorage.getInstance();
//        storageRef = storage.getReference();
//
//        StorageReference imageRef = storageRef.child(imagePath);
//        final long ONE_MEGABYTE = 1024 * 1024;
//        imageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(bytes -> {
//            // Successfully downloaded image data
//            downloadedBytes[0] = bytes;
//
//        }).addOnFailureListener(exception -> {
//            Log.w("Image Retrieval", "Error downloading image", exception);
//            downloadedBytes[0] = null;
//        });
//        return downloadedBytes[0];
//    }
}