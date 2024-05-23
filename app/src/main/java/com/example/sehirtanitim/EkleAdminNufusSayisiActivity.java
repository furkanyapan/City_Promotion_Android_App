package com.example.sehirtanitim;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EkleAdminNufusSayisiActivity extends AppCompatActivity {
    private Button btnEkle, btnAdminMain;
    private EditText edtIlceAdi, edt2021, edt2022, edt2023;
    private SQLiteDatabase vt;
    private Veritabani db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ekle_admin_nufus_sayisi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = new Veritabani(this);

        btnEkle = findViewById(R.id.btnEkle);
        btnAdminMain = findViewById(R.id.btnAdminMain);
        edtIlceAdi = findViewById(R.id.edtIlceAdi);
        edt2021 = findViewById(R.id.edt2021);
        edt2022 = findViewById(R.id.edt2022);
        edt2023 = findViewById(R.id.edt2023);

        btnEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vt = db.getWritableDatabase();
                String sorgu = "INSERT INTO Tablo1 (ilce_name, nufus_2021, nufus_2022, nufus_2023) VALUES ('" + edtIlceAdi.getText().toString() + "','" + edt2021.getText().toString() + "','" + edt2022.getText().toString() + "','" + edt2023.getText().toString() + "')";
                vt.execSQL(sorgu);
                Toast.makeText(getApplicationContext(),"Kayıt Eklendi",Toast.LENGTH_SHORT).show();
            }
        });

        btnAdminMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EkleAdminNufusSayisiActivity.this, AdminMainActivity.class));
            }
        });

    }
}