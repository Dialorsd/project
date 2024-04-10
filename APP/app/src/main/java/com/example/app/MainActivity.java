package com.example.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private Button buttonRL;
    private Button button_login;
    private EditText phoneL;
    private EditText passwordL;

    private SharedPreferences sharedPreferences; // SharedPreferences for storing user data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        buttonRL = findViewById(R.id.buttonRL); // Assuming buttonRL is the register button
        button_login = findViewById(R.id.button_login);
        phoneL = findViewById(R.id.phoneL);
        passwordL = findViewById(R.id.passwordL); // Assuming passwordL is the password field

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = phoneL.getText().toString();
                String password = passwordL.getText().toString();

                if (isValidLogin(phone) && isValidPassword(password)) {
                    String savedPhone = sharedPreferences.getString("phone", "");
                    String savedPassword = sharedPreferences.getString("password", "");

                    if (phone.equals(savedPhone) && password.equals(savedPassword)) {
                        // Login successful
                        Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, MainPage.class);
                        startActivity(intent);
                        // Intent intent = new Intent(MainActivity.this, LoggedInActivity.class);
                        // startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid phone number or password!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Invalid phone number or password!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean isValidLogin(String login) {
        // Перевірка довжини
        if (login.length() != 10) {
            return false;
        }
        // Перевірка на цифри
        for (char c : login.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        // Логін дійсний
        return true;
    }

    public boolean isValidPassword(String password) {
        // Use the same password validation from your registration activity here
        Pattern PASSWORD_PATTERN
                = Pattern.compile(
                "[a-zA-Z0-9\\!\\@\\#\\$]{8,24}");

        return !TextUtils.isEmpty(password) && PASSWORD_PATTERN.matcher(password).matches();
    }

    public void RegestrationMove(View v) {
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }

    // No need for MainPageMove function as there's no "MainPage" class mentioned

    private void checkForSavedLogin() {
        // You can optionally add a function to check for saved login credentials
        // in SharedPreferences and automatically log the user in if they exist
    }
}