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

public class SilAdminTarihiEserlerActivity extends AppCompatActivity {
    private Button btnAdminMain, btnEvet;
    private TextView textViewIlceAdi, textViewEserAdi ,textViewBilgi;
    private Veritabani vt;
    private int idisi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sil_admin_tarihi_eserler);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        vt = new Veritabani(this);
        SQLiteDatabase db = vt.getReadableDatabase();

        btnAdminMain = findViewById(R.id.btnAdminMain);
        btnEvet = findViewById(R.id.btnEvet);
        textViewIlceAdi = findViewById(R.id.textViewIlceAdi);
        textViewEserAdi = findViewById(R.id.textViewEserAdi);
        textViewBilgi = findViewById(R.id.textViewBilgi);

        idisi = getIntent().getIntExtra("idisi", -1);

        if(idisi !=-1){
            String sorgu = "SELECT * FROM Tablo3 WHERE nufus_id ='" + idisi + "'";
            Cursor c = db.rawQuery(sorgu, null);
            if (c != null && c.moveToFirst()) {
                String mesajIlceAdi = "İlçe Adı : " + c.getString(1);
                String mesajEserAdi = "Tarihi Eser : " + c.getString(5);
                String mesajBilgi = "Kaydını silmek istediğinizden emin misiniz?";
                textViewIlceAdi.setText(mesajIlceAdi);
                textViewEserAdi.setText(mesajEserAdi);
                textViewBilgi.setText(mesajBilgi);
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
                String sorgu = "DELETE FROM Tablo3 WHERE nufus_id='" + idisi + "'";
                try {
                    db.execSQL(sorgu);
                    Toast.makeText(SilAdminTarihiEserlerActivity.this, "Kayıt başarıyla silindi.", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(SilAdminTarihiEserlerActivity.this, "Kayıt silinirken hata oluştu.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnAdminMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SilAdminTarihiEserlerActivity.this, AdminMainActivity.class));
            }
        });
    }
}