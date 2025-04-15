package com.example.hamburgueriaz.presentation.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hamburgueriaz.R;
import com.example.hamburgueriaz.domain.Burger;
import com.google.android.material.card.MaterialCardView;

public class BurgerViewHolder extends RecyclerView.ViewHolder {
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
