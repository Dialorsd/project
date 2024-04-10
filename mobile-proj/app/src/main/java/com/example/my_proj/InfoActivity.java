package com.example.my_proj;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class InfoActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button workersReturnToMenu;
    private Button workers_add_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        recyclerView = findViewById(R.id.recyclerViewInfo);
        workersReturnToMenu = findViewById(R.id.infoReturnToMenu);
        workers_add_btn = findViewById(R.id.info_add_btn);

        List<Item> items = new ArrayList<Item>();
        items.add(new Item("John", "0960123822"));
        items.add(new Item("Nastya", "0960123844"));
        items.add(new Item("Michael", "0960123866"));
        items.add(new Item("Leo", "0960123899"));
        items.add(new Item("Lesli", "0960123100"));
        items.add(new Item("Sophia", "0960303822"));
        items.add(new Item("David", "0980125822"));
        items.add(new Item("Joel", "0970305822"));
        items.add(new Item("Max", "0970305850"));
        items.add(new Item("Vlad", "0974305822"));


        MyAdapter adapter = new MyAdapter(getApplicationContext(), items);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), items));

        workersReturnToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushToMenu();
            }
        });
        workers_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v, items);
            }
        });

    }
    private void showPopupWindow(View view, List<Item> items) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_layout_add, null);
        PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        EditText editName = popupView.findViewById(R.id.add_name);
        EditText editPosition = popupView.findViewById(R.id.add_number);
        Button saveButton = popupView.findViewById(R.id.add_save_button);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewInfo);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();
                String position = editPosition.getText().toString();

                items.add(new Item(name, position));
                recyclerView.getAdapter().notifyItemInserted(items.size() - 1);

                popupWindow.dismiss();
            }
        });

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
    }
    private void pushToMenu() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
