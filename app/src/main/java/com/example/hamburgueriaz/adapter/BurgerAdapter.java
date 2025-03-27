package com.example.hamburgueriaz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hamburgueriaz.R;
import com.example.hamburgueriaz.model.Burger;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class BurgerAdapter extends RecyclerView.Adapter<BurgerAdapter.BurgerViewHolder> {
    private final List<Burger> burgers;
    private final OnItemClickListener onItemClickListener;
    private int selectedPosition = -1;

    public BurgerAdapter(List<Burger> burgers, OnItemClickListener onItemClickListener) {
        this.burgers = burgers;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public BurgerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_burger_card,
                parent,
                false
        );
        return new BurgerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BurgerViewHolder holder, int position) {
        Burger burger = burgers.get(position);

        holder.bind(burger, holder.getAdapterPosition() == selectedPosition);

        holder.itemView.setOnClickListener(v -> {
            int previousSelected = selectedPosition;
            selectedPosition = holder.getAdapterPosition();

            if (previousSelected != RecyclerView.NO_POSITION) {
                notifyItemChanged(previousSelected);
            }
            notifyItemChanged(selectedPosition);
            onItemClickListener.onItemClick(burgers.get(selectedPosition));
        });
    }

    @Override
    public int getItemCount() {
        return burgers.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Burger burger);
    }

    public static class BurgerViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView cardView;
        ImageView burgerImage;
        TextView burgerName, burgerDescription, burgerPrice;

        public BurgerViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            burgerImage = itemView.findViewById(R.id.card_view_image);
            burgerName = itemView.findViewById(R.id.card_view_title);
            burgerDescription = itemView.findViewById(R.id.card_view_description);
            burgerPrice = itemView.findViewById(R.id.card_view_price);
        }

        public void bind(Burger burger, boolean isSelected) {
            Context context = itemView.getContext();
            burgerName.setText(burger.getName());
            burgerImage.setImageResource(burger.getImageLocation());
            burgerDescription.setText(burger.getDescription());
            burgerPrice.setText(burger.getPrice());

            cardView.setStrokeColor(
                    ContextCompat.getColor(
                            itemView.getContext(),
                            isSelected ? R.color.selected : R.color.primary_dark
                    )
            );
            cardView.setCardBackgroundColor(ContextCompat.getColor(
                            context,
                            isSelected ? R.color.selected_background : R.color.transparent
                    )
            );
            cardView.setStrokeWidth(isSelected ? 8 : 1);
            cardView.postInvalidate();
        }
    }
}
