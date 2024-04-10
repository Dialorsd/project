package com.example.my_proj;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {

    private Button registrationActivityBtn, registrationActivityBackBtn;
    private EditText registrationActivityEmail;
    private EditText registrationActivityPass;
    private EditText registrationActivityPassRepeat;
    private EditText registrationActivityLogin;
    private EditText registrationActivityName;
    private EditText registrationActivitySurname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        registrationActivityBtn = findViewById(R.id.registrationActivityBtn);
        registrationActivityBackBtn = findViewById(R.id.registrationActivityBackBtn);
        registrationActivityEmail = findViewById(R.id.registrationActivityEmail);
        registrationActivityPass = findViewById(R.id.registrationActivityPass);
        registrationActivityPassRepeat = findViewById(R.id.registrationActivityPassRepeat);
        registrationActivityLogin = findViewById(R.id.registrationActivityLogin);
        registrationActivityName = findViewById(R.id.registrationActivityName);
        registrationActivitySurname = findViewById(R.id.registrationActivitySurname);

        registrationActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = registrationActivityEmail.getText().toString();
                String pass = registrationActivityPass.getText().toString();
                String passRepeat = registrationActivityPassRepeat.getText().toString();
                String login = registrationActivityLogin.getText().toString();
                String name = registrationActivityName.getText().toString();
                String surname = registrationActivitySurname.getText().toString();
                if (isValidEmail(email) && isValidPassword(pass) && pass.equals(passRepeat) && isValidLogin(login) && isValidName(name) && isValidName(surname)) {
                    saveData(name, pass, login, email, surname);
                    pushToMenu();
                } else {
                    showInstruction("Fill in all fields correctly!");
                }

            }
        });
        registrationActivityBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushToLogin();
            }
        });
    }

    public boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public boolean isValidName(String name) {
        String namePattern = "^[a-zA-Z]{6,24}$";
        return Pattern.matches(namePattern, name);
    }

    public boolean isValidPassword(String s) {
        Pattern PASSWORD_PATTERN
                = Pattern.compile(
                "[a-zA-Z0-9\\!\\@\\#\\$]{8,24}");

        return !TextUtils.isEmpty(s) && PASSWORD_PATTERN.matcher(s).matches();
    }

    public boolean isValidLogin(String login) {
        String loginPattern = "^[a-zA-Z0-9]{6,24}$";
        return Pattern.matches(loginPattern, login);
    }

    private void pushToMenu() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
    private void pushToLogin() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void showInstruction(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
    private void saveData(String name, String pass, String login, String email, String surname) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", email);
        editor.putString("password", pass);
        editor.putString("login", login);
        editor.putString("name", name);
        editor.putString("surname", surname);
        editor.apply();
    }
}
