package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Tarifs extends AppCompatActivity {

    private ListView listView; // Змінна для списку тарифів

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tarifs); // Замініть на ваш файл розмітки

        // Ініціалізація списку тарифів
        listView = findViewById(R.id.list_view); // Ідентифікатор вашого ListView

        // Створити список тарифів (замініть на реальні дані)
        String[] tariffs = new String[]{
                "Тариф 1: Безліміт дзвінки, 10 GB інтернету",
                "Тариф 2: 200 хвилин, 5 GB інтернету",
                "Тариф 3: Безліміт інтернет",
                "Тариф 4: Безліміт дзвінки",
                "Тариф 5: 1000 хв 15GB",
                "Тариф 6: Роумінг 100хв",
                "Тариф 7: 20 GB 1500 хв",
                "Тариф 8: 7 GB 400 хв",
                "Тариф 9: 5 Gb 200 хв",
                "Тариф 10: 2000 хв 25 GB",
                "Тариф 11: Безліміт на місяць"
        };

        // Створити адаптер для ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, tariffs);

        // Встановити адаптер для ListView
        listView.setAdapter(adapter);

        // Додавання відступів для системних панелей (необов'язково)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
    public void backMain(View v){
        Intent intent = new Intent(this,MainPage.class);
        startActivity(intent);
    }
}