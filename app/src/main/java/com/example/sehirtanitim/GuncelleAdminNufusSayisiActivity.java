package com.example.sehirtanitim;

import android.content.Intent;
import android.database.Cursor;
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

public class GuncelleAdminNufusSayisiActivity extends AppCompatActivity {
    private Button btnGuncelle, btnAdminMain;
    private EditText edtIlceAdi, edt2021, edt2022, edt2023;
    private Veritabani vt;
    int idisi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_guncelle_admin_nufus_sayisi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnGuncelle = findViewById(R.id.btnGuncelle);
        btnAdminMain = findViewById(R.id.btnAdminMain);
        edtIlceAdi = findViewById(R.id.edtIlceAdi);
        edt2021 = findViewById(R.id.edt2021);
        edt2022 = findViewById(R.id.edt2022);
        edt2023 = findViewById(R.id.edt2023);

        vt = new Veritabani(this);
        SQLiteDatabase db = vt.getReadableDatabase();
        idisi = getIntent().getIntExtra("idisi", -1);
        String sorgu="SELECT * FROM Tablo1 WHERE nufus_id='"+idisi+"'";
        Cursor c = db.rawQuery(sorgu, null);
        c.moveToFirst();
        edtIlceAdi.setText(c.getString(1));
        edt2021.setText(""+c.getString(2));
        edt2022.setText(""+c.getString(3));
        edt2023.setText(""+c.getString(4));

        btnGuncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = vt.getWritableDatabase();
                String sorgu = "UPDATE Tablo1 SET ilce_name='"+edtIlceAdi.getText().toString()+"',nufus_2021='"+edt2021.getText().toString()+"',nufus_2022='"+edt2022.getText().toString()+"',nufus_2023='"+edt2023.getText().toString()+"' WHERE nufus_id='"+idisi+"'";
                try {
                    db.execSQL(sorgu);
                    Toast.makeText(GuncelleAdminNufusSayisiActivity.this, "Kayıt başarıyla güncellendi.", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(GuncelleAdminNufusSayisiActivity.this, "Kayıt güncellenirken hata oluştu.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnAdminMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuncelleAdminNufusSayisiActivity.this, AdminMainActivity.class));
            }
        });

    }
}