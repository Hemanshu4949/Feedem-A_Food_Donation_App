package com.example.feedem.ui.profile;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.feedem.ModelClasses.Donation_Data;
import com.example.feedem.ModelClasses.Profile_Data;
import com.example.feedem.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Edit_Profile extends Fragment {

    ImageView profilepicture;
    private StorageReference storageRef;
    ProgressDialog pd;

    private static final int CAMERA_REQUEST = 100;
    private static final int STORAGE_REQUEST = 200;
    private static final int IMAGE_PICK_GALLERY_REQUEST = 300;
    private static final int IMAGE_PICK_CAMERA_REQUEST = 400;

    Uri imageUri;

    EditText name , email , Contact_no , age ;


    private static final int IMAGE_PICK_REQUEST = 100;
    private ActivityResultLauncher<Intent> pickImageLauncher;
    MaterialButton submit;



    public Edit_Profile() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        profilepicture = view.findViewById(R.id.setting_profile_image);
        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        Contact_no = view.findViewById(R.id.contact);
        age = view.findViewById(R.id.age);

        if (Profile_Data.agecheck())
        {
            age.setText(Profile_Data.getAge());
        }
        if (Profile_Data.namecheck())
        {
            name.setText(Profile_Data.getName());
        }
        if (Profile_Data.emailcheck())
        {
            email.setText(Profile_Data.getEmail());
        }
        if (Profile_Data.contactcheck())
        {
            Contact_no.setText(Profile_Data.getContact_no());
        }
        if (Profile_Data.imagecheck())
        {
            profilepicture.setImageBitmap(Profile_Data.getImage());
        }

        pd = new ProgressDialog(getContext());
        pd.setCanceledOnTouchOutside(false);

        submit = view.findViewById(R.id.Submitbtn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                if (TextUtils.isEmpty(name.getText()) && TextUtils.isEmpty(age.getText()) && TextUtils.isEmpty(email.getText()) && TextUtils.isEmpty(Contact_no.getText()) ) {
                    Toast.makeText(getContext() , "FillUp All Fields" , Toast.LENGTH_SHORT).show();
                }
                else {
                    Map<String, Object> profile_upload_data = new HashMap<>();
                    profile_upload_data.put("Contact_no", Contact_no.getText().toString());
                    profile_upload_data.put("name", name.getText().toString());
                    profile_upload_data.put("email", email.getText().toString());
                    profile_upload_data.put("age", age.getText().toString());

                    db.collection("Profile").document(Donation_Data.getUid())
                            .set(profile_upload_data)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getContext() , "Data Successfully Updated" , Toast.LENGTH_SHORT).show();
                                    Log.d("TAG", "DocumentSnapshot successfully written!");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("TAG", "Error writing document", e);
                                }
                            });

                }
                }
        });
        pickImageLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                imageUri = result.getData().getData();
                // Handle the selected image (traditional or SAF)
                if (isAndroidVersionGreaterThanOrEqualTo(Build.VERSION_CODES.Q)) {
                    openImageWithSAF(imageUri);
                } else {
                    setImageViewFromUri(imageUri);
                }
            }
        });
        profilepicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickFromGallery();

            }
        });

        return view;
    }
    private void pickFromGallery() {
        if (isAndroidVersionGreaterThanOrEqualTo(Build.VERSION_CODES.Q)) {
            pickImageWithSAF();
        } else {
            pickImageWithLegacyStorage();
        }
    }

    private void pickImageWithLegacyStorage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        pickImageLauncher.launch(intent);
    }

    private void pickImageWithSAF() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        pickImageLauncher.launch(intent);
    }

    private void openImageWithSAF(Uri uri) {
        try {
            ContentResolver contentResolver = requireContext().getContentResolver();
            InputStream inputStream = contentResolver.openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            profilepicture.setImageBitmap(bitmap);
            uploadImageToFirebase(uri);
            // Use the InputStream to process the image data (e.g., display in ImageView)
            // ...
        } catch (IOException e) {
            e.printStackTrace();
            // Handle potential exceptions
        }
    }

    private void setImageViewFromUri(Uri uri) {
        // Set the image to the ImageView using Glide or Picasso library (recommended)
        // or using setImageURI directly
        Glide.with(this).load(uri).into(profilepicture);  // Using Glide
        // imageView.setImageURI(uri);  // Using setImageURI
    }

    private boolean isAndroidVersionGreaterThanOrEqualTo(int apiLevel) {
        return Build.VERSION.SDK_INT >= apiLevel;
    }

    private  void uploadImageToFirebase(Uri imageUri) {
        if (imageUri != null) {
            pd.setMessage("Uploading...");
            pd.show();

            StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("profile_images");
            StorageReference imageRef = storageRef.child(Donation_Data.getUid()+".jpg");

            imageRef.putFile(imageUri)
                    .addOnSuccessListener(taskSnapshot -> {
                        // Image uploaded successfully
                        pd.dismiss();
                        Toast.makeText(getContext(), "Image uploaded successfully", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(exception -> {
                        // Handle any errors
                        pd.dismiss();
                        Toast.makeText(getContext(), "Failed to upload image", Toast.LENGTH_SHORT).show();
                    });
        }
    }



}