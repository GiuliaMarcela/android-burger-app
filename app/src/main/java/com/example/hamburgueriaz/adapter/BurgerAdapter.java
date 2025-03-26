package com.example.hamburgueriaz.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hamburgueriaz.R;
import com.example.hamburgueriaz.model.Burger;

import java.util.List;

public class BurgerAdapter extends RecyclerView.Adapter<BurgerAdapter.BurgerViewHolder> {
    private List<Burger> burgers;

    public BurgerAdapter(List<Burger> burgers) {
        this.burgers = burgers;
    }

    @NonNull
    @Override
    public BurgerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_burger_card, parent, false);
        return new BurgerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BurgerViewHolder holder, int position) {
        Burger burger = burgers.get(position);
        holder.burgerName.setText(burger.getName());
        holder.burgerImage.setImageResource(burger.getImageLocation());
        holder.burgerDescription.setText(burger.getDescription());
        holder.burgerPrice.setText(burger.getPrice());
    }

    @Override
    public int getItemCount() {
        return burgers.size();
    }

    public static class BurgerViewHolder extends RecyclerView.ViewHolder {
        ImageView burgerImage;
        TextView burgerName;
        TextView burgerDescription;
        TextView burgerPrice;

        public BurgerViewHolder(View itemView) {
            super(itemView);
            burgerImage = itemView.findViewById(R.id.card_view_image);
            burgerName = itemView.findViewById(R.id.card_view_title);
            burgerDescription = itemView.findViewById(R.id.card_view_description);
            burgerPrice = itemView.findViewById(R.id.card_view_price);
        }
    }
}
