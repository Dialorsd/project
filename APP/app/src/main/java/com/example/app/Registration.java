package com.example.app;

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

public class Registration extends AppCompatActivity {

    private EditText editNameR;
    private EditText editNameRC;
    private EditText editTextTextEmailAddress;
    private EditText editTextPasswordR;
    private EditText editTextPasswordRC;
    private Button buttonReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regestration);

        editNameR = findViewById(R.id.editTextName);
        editNameRC = findViewById(R.id.editTextSurname);
        editTextTextEmailAddress = findViewById(R.id.editTextEmail);
        editTextPasswordR = findViewById(R.id.editTextPassword);
        editTextPasswordRC = findViewById(R.id.editTextRepeatPassword);
        buttonReg = findViewById(R.id.buttonRegister);

        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextTextEmailAddress.getText().toString();
                String pass = editTextPasswordR.getText().toString();
                String passRepeat = editTextPasswordRC.getText().toString();
                String name = editNameR.getText().toString();
                String surname = editNameRC.getText().toString();
                if (isValidEmail(email) && isValidPassword(pass) && pass.equals(passRepeat) && isValidName(name) && isValidName(surname)) {
                    saveData(name, pass, email, surname);
                    pushToMenu();
                } else {
                    showInstruction("Fill in all fields correctly!");
                }

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
        Pattern PASSWORD_PATTERN = Pattern.compile("[a-zA-Z0-9\\!\\@\\#\\$]{8,24}");
        return !TextUtils.isEmpty(s) && PASSWORD_PATTERN.matcher(s).matches();
    }

    private void pushToMenu() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void showInstruction(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    private void saveData(String name, String pass, String email, String surname) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", email);
        editor.putString("password", pass);
        editor.putString("name", name);
        editor.putString("surname", surname);
        editor.apply();
    }

    public void backMain(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
