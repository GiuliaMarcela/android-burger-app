package com.example.hamburgueriaz.presentation;


import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hamburgueriaz.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        MaterialButton buttonContinue = findViewById(R.id.continue_button);
        TextInputLayout textInputLayout = findViewById(R.id.name_input_layout);
        TextInputEditText textInputEditText = findViewById(R.id.name_input_edit_text);

        buttonContinue.setOnClickListener(v -> {
            String name = Objects.requireNonNull(textInputEditText.getText()).toString().trim();

            if (name.isEmpty()) {
                textInputLayout.setError(getString(R.string.required_field));
            } else {
                textInputLayout.setError(null);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("CUSTOMER_NAME", name);
                startActivity(intent);
                finish();
            }
        });
    }
}
