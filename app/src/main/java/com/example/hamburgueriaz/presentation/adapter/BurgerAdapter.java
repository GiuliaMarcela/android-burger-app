package com.example.hamburgueriaz.presentation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hamburgueriaz.R;
import com.example.hamburgueriaz.domain.Burger;
import com.example.hamburgueriaz.presentation.view.BurgerViewHolder;

import java.util.List;

public class BurgerAdapter extends RecyclerView.Adapter<BurgerViewHolder> {
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
}
