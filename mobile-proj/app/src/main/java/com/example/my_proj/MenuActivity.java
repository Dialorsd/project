package com.example.my_proj;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {
    private Button menuActivityLogout, menuActivityProfile;
    private Button menuActivityBtnEmployees, menuActivityBtnProjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        menuActivityLogout = findViewById(R.id.menuActivityLogout);
        menuActivityBtnEmployees = findViewById(R.id.menuActivityBtnEmployees);
        menuActivityBtnProjects = findViewById(R.id.menuActivityBtnProjects);
        menuActivityProfile = findViewById(R.id.menuActivityProfile);

        menuActivityLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushToLogin();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("isAuthed", "false");
                editor.apply();
                finishAffinity();
            }
        });
        menuActivityBtnEmployees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushToWorkers();
            }
        });
        menuActivityBtnProjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushToProjects();
            }
        });
        menuActivityProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushToProfile();
            }
        });
    }
    private void pushToLogin() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    private void pushToWorkers() {
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }
    private void pushToProjects() {
        Intent intent = new Intent(this, ContactsActivity.class);
        startActivity(intent);
    }
    private void pushToProfile() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}
