package com.example.my_proj;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button loginActivityBtnReg;
    private Button loginActivityBtnLogin;
    private EditText loginActivityInputName;
    private EditText loginActivityInputPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginActivityBtnReg = findViewById(R.id.loginActivityBtnReg);
        loginActivityBtnLogin = findViewById(R.id.loginActivityBtnLogin);
        loginActivityInputName = findViewById(R.id.loginActivityInputName);
        loginActivityInputPass = findViewById(R.id.loginActivityInputPass);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String isAuthed = sharedPreferences.getString("isAuthed", "false");
        if(isAuthed.equals("true")){
            pushToMenu();
        }
        loginActivityBtnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                onClickRegistration();
            }
        });
        loginActivityBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String savedLogin = sharedPreferences.getString("login", "");
                String savedPass = sharedPreferences.getString("password", "");
                if(loginActivityInputName.getText().toString().equals(savedLogin) && loginActivityInputPass.getText().toString().equals(savedPass)){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("isAuthed", "true");
                    editor.apply();
                    pushToMenu();
                }
                else{
                    showInstruction("Wrong data");
                }
            }
        });
    }

    private void onClickRegistration() {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }
    private void pushToMenu() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
    private void showInstruction(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}

