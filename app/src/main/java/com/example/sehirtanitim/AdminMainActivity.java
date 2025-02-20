package com.example.sehirtanitim;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdminMainActivity extends AppCompatActivity {
    private Button btnMain, btnNufusSayisi, btnNufusDagilimi, btnTarihiEser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnMain = findViewById(R.id.btnMain);
        btnNufusSayisi = findViewById(R.id.btnNufusSayisi);
        btnNufusDagilimi = findViewById(R.id.btnNufusDagilimi);
        btnTarihiEser = findViewById(R.id.btnTarihiEser);

        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent (AdminMainActivity.this, MainActivity.class));
            }
        });

        btnNufusSayisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent (AdminMainActivity.this, AdminNufusSayisiActivity.class));
            }
        });

        btnNufusDagilimi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent (AdminMainActivity.this, AdminNufusDagilimiActivity.class));
            }
        });

        btnTarihiEser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent (AdminMainActivity.this, AdminTarihiEserlerActivity.class));
            }
        });
    }
}