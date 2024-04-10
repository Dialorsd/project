package com.example.app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class dialog_recharge extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_recharge);

        EditText rechargeAmountEditText = findViewById(R.id.recharge_amount);
        Button rechargeButton = findViewById(R.id.recharge_button);

        // Обробник кліку кнопки "Поповнити"
        rechargeButton.setOnClickListener(view -> {
            // Отримуємо введене користувачем число
            String amountStr = rechargeAmountEditText.getText().toString();
            if (!amountStr.isEmpty()) {
                int amount = Integer.parseInt(amountStr);

                // Передаємо введене число назад до MainPage через Intent
                setResult(amount);
            }

            // Закриваємо поточну активність
            finish();
        });
    }
}