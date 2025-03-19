package com.example.hamburgueriaz;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView textViewQuantity;
    private int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textViewQuantity = findViewById(R.id.text_quantity);
        Button btnDecrease = findViewById(R.id.btn_decrease);
        Button btnIncrease = findViewById(R.id.btn_increase);

        btnDecrease.setOnClickListener(v -> decreaseQuantity());
        btnIncrease.setOnClickListener(v -> increaseQuantity());
    }

    private void decreaseQuantity() {
        if (quantity > 1) {
            quantity--;
            textViewQuantity.setText(String.valueOf(quantity));
        }
    }

    private void increaseQuantity() {
        quantity++;
        textViewQuantity.setText(String.valueOf(quantity));
    }
}