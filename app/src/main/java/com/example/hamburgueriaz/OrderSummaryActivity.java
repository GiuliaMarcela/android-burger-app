package com.example.hamburgueriaz;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class OrderSummaryActivity extends AppCompatActivity {

    private static final double PRECO_BASE = 20.00;
    private static final double PRECO_BACON = 2.00;
    private static final double PRECO_QUEIJO = 2.00;
    private static final double PRECO_ONION_RINGS = 3.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_summary);

        TextView burgerNameText = findViewById(R.id.title_order_burger);
        TextView burgerDescriptionText = findViewById(R.id.description_order_burger);
        TextView burgerPriceText = findViewById(R.id.price_order_burger);

        String burgerName = getIntent().getStringExtra("BURGER_NAME");
        String burgerDescription = getIntent().getStringExtra("BURGER_DESCRIPTION");
        String burgerPrice = getIntent().getStringExtra("BURGER_PRICE");

        burgerNameText.setText(burgerName);
        burgerPriceText.setText(burgerPrice);
        burgerDescriptionText.setText(burgerDescription);
    }

    private double calcularTotal(int quantidade, boolean temBacon, boolean temQueijo, boolean temOnionRings) {
        double total = PRECO_BASE * quantidade;

        if (temBacon) {
            total += PRECO_BACON * quantidade;
        }
        if (temQueijo) {
            total += PRECO_QUEIJO * quantidade;
        }
        if (temOnionRings) {
            total += PRECO_ONION_RINGS * quantidade;
        }

        return total;
    }
}