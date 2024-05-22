package com.example.sehirtanitim;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SilAdminNufusSayisiActivity extends AppCompatActivity {
    private Button btnAdminMain, btnEvet;
    private TextView textViewBilgi;
    private Veritabani vt;
    private int idisi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sil_admin_nufus_sayisi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        vt = new Veritabani(this);
        SQLiteDatabase db = vt.getReadableDatabase();

        btnAdminMain = findViewById(R.id.btnAdminMain);
        btnEvet = findViewById(R.id.btnEvet);
        textViewBilgi = findViewById(R.id.textViewBilgi);

        idisi = getIntent().getIntExtra("idisi", -1);

        if(idisi !=-1){
            String sorgu = "SELECT * FROM Tablo WHERE nufus_id ='" + idisi + "'";
            Cursor c = db.rawQuery(sorgu, null);
            if (c != null && c.moveToFirst()) {
                String mesaj = "İlçe Adı : " + c.getString(1) + " 2021 Nufusu : " + c.getInt(2) +" 2022 Nufusu : " + c.getInt(3) + " 2023 Nufusu : " + c.getInt(4) + " kaydını silmek istiyor musunuz?";
                textViewBilgi.setText(mesaj);
                c.close();
            } else {
                textViewBilgi.setText("Kayıt bulunamadı.");
            }
        } else {
            textViewBilgi.setText("Geçersiz kayıt ID'si.");
        }

        btnEvet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = vt.getWritableDatabase();
                String sorgu = "DELETE FROM Tablo WHERE nufus_id='" + idisi + "'";
                try {
                    db.execSQL(sorgu);
                    Toast.makeText(SilAdminNufusSayisiActivity.this, "Kayıt başarıyla silindi.", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(SilAdminNufusSayisiActivity.this, "Kayıt silinirken hata oluştu.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnAdminMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SilAdminNufusSayisiActivity.this, AdminMainActivity.class));
            }
        });
    }
}