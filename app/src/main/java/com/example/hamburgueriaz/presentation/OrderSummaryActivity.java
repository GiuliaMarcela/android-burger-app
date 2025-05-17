package com.example.hamburgueriaz.presentation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.CompoundButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.hamburgueriaz.R;
import com.example.hamburgueriaz.databinding.ActivityOrderSummaryBinding;

import java.util.Locale;
import java.util.Objects;

public class OrderSummaryActivity extends AppCompatActivity {

    private Intent intent;
    private int currentQuantity = 1;
    private ActivityOrderSummaryBinding orderSummaryBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        orderSummaryBinding = ActivityOrderSummaryBinding.inflate(getLayoutInflater());
        ConstraintLayout rootView = orderSummaryBinding.getRoot();
        setContentView(rootView);

        intent = getIntent();

        displayBurgerDetails();
        onClickOrCheckedListeners();
        orderSummaryBinding.completeOrderButton.setOnClickListener(view -> handleCompleteOrderByEmail());
    }

    private void updateQuantityDisplay() {
        String quantity = String.valueOf(currentQuantity);
        orderSummaryBinding.quantityTextView1.setText(quantity);
        orderSummaryBinding.quantityTextView2.setText(quantity);
    }

    private void handleCompleteOrderByEmail() {
        String customerName = intent.getStringExtra("CUSTOMER_NAME");
        String burgerName = intent.getStringExtra("BURGER_NAME");
        String burgerPrice = intent.getStringExtra("BURGER_PRICE");

        String hasBacon = orderSummaryBinding.checkBacon.isChecked() ? getString(R.string.yes) : getString(R.string.no);
        String hasCheese = orderSummaryBinding.checkQueijo.isChecked() ? getString(R.string.yes) : getString(R.string.no);
        String hasOnionRings = orderSummaryBinding.checkOnionRings.isChecked() ? getString(R.string.yes) : getString(R.string.no);

        double finalPrice = calculateFinalPrice(burgerPrice);

        @SuppressLint("DefaultLocale") String emailBody = String.format(
                "%s:\n\n" +
                        "%s: %s\n" +
                        "%s: %s\n" +
                        "%s %s\n" +
                        "%s %s\n" +
                        "%s %s\n" +
                        "%s: %d\n" +
                        "%s: R$ %.2f",
                getString(R.string.title_order_summary),
                getString(R.string.customer_name),
                customerName,
                getString(R.string.burger_title),
                burgerName,
                getString(R.string.has_bacon),
                hasBacon,
                getString(R.string.has_cheese),
                hasCheese,
                getString(R.string.has_onion_rings),
                hasOnionRings,
                getString(R.string.quantity_title_details),
                currentQuantity,
                getString(R.string.total_amount),
                finalPrice
        );

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject, customerName));
        emailIntent.putExtra(Intent.EXTRA_TEXT, emailBody);

        startActivity(emailIntent);
    }

    private void displayBurgerDetails() {
        double burgerPrice = Double.parseDouble(Objects.requireNonNull(intent.getStringExtra("BURGER_PRICE")));

        orderSummaryBinding.titleOrderBurger.setText(intent.getStringExtra("BURGER_NAME"));
        orderSummaryBinding.priceOrderBurger.setText(String.format(getLocale(), "R$ %.2f", burgerPrice));
        orderSummaryBinding.customerNameTextView.setText(intent.getStringExtra("CUSTOMER_NAME"));

        updateQuantityDisplay();
        calculateFinalPrice(intent.getStringExtra("BURGER_PRICE"));
    }

    private void onClickOrCheckedListeners() {
        CompoundButton.OnCheckedChangeListener checkedChangeListener = (buttonView, isChecked) -> calculateFinalPrice(intent.getStringExtra("BURGER_PRICE"));

        orderSummaryBinding.checkBacon.setOnCheckedChangeListener(checkedChangeListener);
        orderSummaryBinding.checkQueijo.setOnCheckedChangeListener(checkedChangeListener);
        orderSummaryBinding.checkOnionRings.setOnCheckedChangeListener(checkedChangeListener);

        orderSummaryBinding.iconAdd.setOnClickListener(view -> increaseQuantity());
        orderSummaryBinding.iconSub.setOnClickListener(view -> decreaseQuantity());
    }

    private double calculateFinalPrice(String burgerPrice) {
        double baconPrice = 2.0;
        double cheesePrice = 2.0;
        double onionRingsPrice = 3.0;
        double total = Double.parseDouble(burgerPrice) * currentQuantity;

        if (orderSummaryBinding.checkBacon.isChecked()) {
            total += baconPrice * currentQuantity;
        }

        if (orderSummaryBinding.checkQueijo.isChecked()) {
            total += cheesePrice * currentQuantity;
        }

        if (orderSummaryBinding.checkOnionRings.isChecked()) {
            total += onionRingsPrice * currentQuantity;
        }

        orderSummaryBinding.hasBaconTextView.setText(orderSummaryBinding.checkBacon.isChecked() ? getString(R.string.yes) : getString(R.string.no));
        orderSummaryBinding.hasCheeseTextView.setText(orderSummaryBinding.checkQueijo.isChecked() ? getString(R.string.yes) : getString(R.string.no));
        orderSummaryBinding.hasOnionRingsTextView.setText(orderSummaryBinding.checkOnionRings.isChecked() ? getString(R.string.yes) : getString(R.string.no));
        orderSummaryBinding.totalAmountTextView.setText(String.format(getLocale(), "R$ %.2f", total));

        return total;
    }

    private void decreaseQuantity() {
        if (currentQuantity > 1) {
            currentQuantity--;
            updateQuantityDisplay();
            calculateFinalPrice(intent.getStringExtra("BURGER_PRICE"));
        }
    }

    private void increaseQuantity() {
        currentQuantity++;
        updateQuantityDisplay();
        calculateFinalPrice(intent.getStringExtra("BURGER_PRICE"));
    }

    private Locale getLocale() {
        return new Locale("pt", "BR");
    }
}