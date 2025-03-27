package com.example.hamburgueriaz;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hamburgueriaz.adapter.BurgerAdapter;
import com.example.hamburgueriaz.model.Burger;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Burger selectedBurger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        List<Burger> burgers = new ArrayList<>();
        burgers.add(new Burger("Egg X-Burger", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer in fermentum ex.", "R$ 20.00", R.drawable.unsplash_burger_photo));
        burgers.add(new Burger("Cheese Burger", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer in fermentum ex.", "R$ 20.00", R.drawable.unsplash_burger_photo));
        burgers.add(new Burger("Chicken Burger", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer in fermentum ex.", "R$ 20.00", R.drawable.unsplash_burger_photo));
        burgers.add(new Burger("Veggie Burger", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer in fermentum ex.", "R$ 20.00", R.drawable.unsplash_burger_photo));

        MaterialButton submitOrderButton = findViewById(R.id.submit_order_button);
        RecyclerView recyclerView = findViewById(R.id.cards_view_rec);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        BurgerAdapter adapter = new BurgerAdapter(burgers, burger -> {
            selectedBurger = burger;
            submitOrderButton.setEnabled(true);
            submitOrderButton.setBackgroundTintList(
                    ColorStateList.valueOf(
                            ContextCompat.getColor(this, R.color.accent_red)
                    )
            );
            submitOrderButton.postInvalidate();
        });

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);
        submitOrderButton.setEnabled(false);

        submitOrderButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, OrderSummaryActivity.class);
            //TODO: Passar dados para a próxima tela
            startActivity(intent);
        });
    }
}