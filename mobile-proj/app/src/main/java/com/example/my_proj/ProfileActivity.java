package com.example.my_proj;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    private Button profileActivityBtnBack;
    private EditText profileName, profileLogin, profileEmail;
    private ImageView profileAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        String savedName = sharedPreferences.getString("name", "--");
        String savedEmail = sharedPreferences.getString("email", "--");
        String savedLogin = sharedPreferences.getString("login", "--");
        String savedAvatar = sharedPreferences.getString("avatar", "--");

        profileAvatar = findViewById(R.id.profileAvatar);
        profileName = findViewById(R.id.profileName);
        profileLogin = findViewById(R.id.profileLogin);
        profileEmail = findViewById(R.id.profileEmail);

        profileName.setText(savedName);
        profileLogin.setText(savedLogin);
        profileEmail.setText(savedEmail);


        if (!savedAvatar.equals("--")) {
            profileAvatar.setImageURI(Uri.parse(savedAvatar));
        }

        profileActivityBtnBack = findViewById(R.id.profileActivityBtnBack);

        profileActivityBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfileData();
                pushToMenu();
            }
        });

        profileAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectProfilePicture();
            }
        });
    }

    private void selectProfilePicture() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            profileAvatar.setImageURI(selectedImage);
            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("avatar", selectedImage.toString());
            editor.apply();
        }
    }

    private void saveProfileData() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (!profileName.getText().toString().isEmpty() && !profileLogin.getText().toString().isEmpty() && !profileEmail.getText().toString().isEmpty()){
            editor.putString("name", profileName.getText().toString());
            editor.putString("email", profileEmail.getText().toString());
            editor.putString("login", profileLogin.getText().toString());
            editor.apply();
        } else {
            showInstruction("Fill in all fields correctly!");
        }
    }

    private void pushToMenu() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    private void showInstruction(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}
