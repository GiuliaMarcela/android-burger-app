package com.example.hamburgueriaz.presentation;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hamburgueriaz.R;
import com.example.hamburgueriaz.data.BurgerRepositoryImpl;
import com.example.hamburgueriaz.domain.Burger;
import com.example.hamburgueriaz.domain.BurgerRepository;
import com.example.hamburgueriaz.presentation.adapter.BurgerAdapter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Burger selectedBurger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        BurgerRepository burgerRepository = new BurgerRepositoryImpl(this);
        List<Burger> burgers = burgerRepository.findAll();

        setupRecyclerView(burgers);

        getOnBackPressedDispatcher().addCallback(this, onBackPressedCallback());
    }

    private OnBackPressedCallback onBackPressedCallback() {
        return new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                new MaterialAlertDialogBuilder(MainActivity.this)
                        .setTitle(getString(R.string.exit_modal_title))
                        .setMessage(R.string.exit_modal_message)
                        .setPositiveButton(R.string.yes, (dialog, which) -> {
                            finish();
                        })
                        .setNegativeButton(R.string.no, null)
                        .show();
            }
        };
    }

    private void setupRecyclerView(List<Burger> burgers) {
        MaterialButton submitOrderButton = findViewById(R.id.submit_order_button);
        RecyclerView recyclerView = findViewById(R.id.cards_view_rec);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        BurgerAdapter burgerAdapter = new BurgerAdapter(burgers, burger -> {
            selectedBurger = burger;
            submitOrderButton.setEnabled(true);
            submitOrderButton.setBackgroundTintList(
                    ColorStateList.valueOf(
                            ContextCompat.getColor(this, R.color.dark_green)
                    )
            );
            submitOrderButton.postInvalidate();
        });
        recyclerView.setAdapter(burgerAdapter);

        submitOrderButton.setOnClickListener(v -> navigateToOrderSummaryActivity());
        submitOrderButton.setEnabled(false);
    }

    private void navigateToOrderSummaryActivity() {
        Intent receivedIntent = getIntent();
        Intent intent = new Intent(MainActivity.this, OrderSummaryActivity.class);
        intent.putExtra("BURGER_NAME", selectedBurger.getName());
        intent.putExtra("BURGER_DESCRIPTION", selectedBurger.getDescription());
        intent.putExtra("BURGER_PRICE", selectedBurger.getPrice());
        intent.putExtra("CUSTOMER_NAME", receivedIntent.getStringExtra("CUSTOMER_NAME"));
        startActivity(intent);
    }
}