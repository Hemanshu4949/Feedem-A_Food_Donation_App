package com.example.feedem.ui.profile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feedem.R;
import com.google.firebase.firestore.GeoPoint;

import java.util.ArrayList;
import java.util.List;

public class Recycle_view_adapter extends RecyclerView.Adapter<Recycle_view_adapter.ViewHolder> {

    public Recycle_view_adapter(ArrayList<String> timestamp, ArrayList<String> status, ArrayList<String> contact, ArrayList<String> food_Categories, ArrayList<Long> food_Hours, ArrayList<GeoPoint> locaiton, ArrayList<Long> servings, ArrayList<String> type_Of_Food, ArrayList<String> address) {
        this.timestamp = timestamp;
        this.status = status;
        this.contact = contact;
        this.Food_Categories = food_Categories;
        this.Food_Hours = food_Hours;
        this.Locaiton = locaiton;
        this.Servings = servings;
        this.Type_Of_Food = type_Of_Food;
        this.address = address;
    }

    ArrayList<String> timestamp = new ArrayList<>();
    ArrayList<String> status = new ArrayList<>();
    ArrayList<String> contact = new ArrayList<>();
    ArrayList<String> Food_Categories = new ArrayList<>();
    ArrayList<Long> Food_Hours = new ArrayList<>();
    ArrayList<GeoPoint> Locaiton = new ArrayList<>();
    ArrayList<Long> Servings = new ArrayList<>();
    ArrayList<String> Type_Of_Food = new ArrayList<>();
    ArrayList<String> address = new ArrayList<>();

    @NonNull
    @Override
    public Recycle_view_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycle_view_design , parent , false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Recycle_view_adapter.ViewHolder holder, int position) {
        holder.status.setText(status.get(position));
        holder.timestamp.setText(timestamp.get(position));
        holder.foodcategory.setText(Food_Categories.get(position));
        holder.typeoffood.setText(Type_Of_Food.get(position));
        holder.Contact.setText(contact.get(position));
        holder.Address.setText(address.get(position));
        holder.Location.setText(Locaiton.get(position).toString());
        holder.Servings.setText(Servings.get(position).toString());
        holder.Food_Hours.setText(Food_Hours.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return status.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView status , timestamp , foodcategory , typeoffood , Contact , Address , Location , Servings , Food_Hours;
        ConstraintLayout cardTitle;
        ConstraintLayout cardDetails;
        ImageView imageExpand;
        boolean isExpanded = false;


        public ViewHolder(View itemView) {
            super(itemView);
            status = itemView.findViewById(R.id.statusvalue);
            timestamp = itemView.findViewById(R.id.timestampvalue);
            foodcategory = itemView.findViewById(R.id.foodcategoryvalue);
            Contact = itemView.findViewById(R.id.contactvalue);
            Address = itemView.findViewById(R.id.Addressvalue);
            Location = itemView.findViewById(R.id.Locaitonvalue);
            Servings = itemView.findViewById(R.id.Servingsvalue);
            Food_Hours = itemView.findViewById(R.id.Foodhoursvalue);
            typeoffood = itemView.findViewById(R.id.Typeoffoodvalue);

            cardTitle = itemView.findViewById(R.id.card_title);
            cardDetails = itemView.findViewById(R.id.card_details);
            imageExpand = itemView.findViewById(R.id.image_expand);
            cardTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toggleCardDetails();
                }
            });


        }
        private void toggleCardDetails() {
            isExpanded = !isExpanded;
            if (isExpanded) {
                cardDetails.setVisibility(View.VISIBLE);
                imageExpand.setImageResource(R.drawable.expand_less); // Change icon to collapse indicator
            } else {
                cardDetails.setVisibility(View.GONE);
                imageExpand.setImageResource(R.drawable.expand_more); // Change icon to expand indicator
            }
        }
    }
}
