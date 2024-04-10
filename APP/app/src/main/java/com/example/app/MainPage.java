package com.example.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainPage extends AppCompatActivity {

    private TextView balanceTextView;
    private Button rechargeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_page);


        balanceTextView = findViewById(R.id.balance_text_view);
        rechargeButton = findViewById(R.id.recharge_button);


        String balance = getBalance();
        balanceTextView.setText(balance);


        rechargeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showRechargeDialog(MainPage.this);
            }
        });


        //ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
          //  Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            //v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            //return insets;
        //});
    }

    private String getBalance() {

        return "Баланс: 100 грн";
    }

    private void showRechargeDialog(Context context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Поповнити рахунок")
                .setView(R.layout.activity_dialog_recharge)
                .setPositiveButton("Поповнити", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String balance = getBalance();
                        balanceTextView.setText(balance);
                    }
                })
                .setNegativeButton("Відміна", null)
                .show();
    }
    public void MainMove(View v){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void TarifMove(View v){
        Intent intent = new Intent(this,Tarifs.class);
        startActivity(intent);
    }
    public void Employee(View v){
        Intent intent = new Intent(this,Employee.class);
        startActivity(intent);
    }
}
