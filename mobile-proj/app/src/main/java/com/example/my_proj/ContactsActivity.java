package com.example.my_proj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContactsActivity extends AppCompatActivity {

    Button projectsReturnToMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        projectsReturnToMenu = findViewById(R.id.projectReturnToMenu);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewProject);

        List<Item> items = new ArrayList<>();
        String[] categories = {"Co-workers", "Friends", "Family"};
        Random random = new Random();

        for (int i = 1; i <= 10; i++) {
            String name = "Contact " + i;
            String category = categories[random.nextInt(categories.length)];
            float price = i;

            items.add(new Item(name, category));
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), items));
        projectsReturnToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushToMenu();
            }
        });
    }

    private void pushToMenu() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
