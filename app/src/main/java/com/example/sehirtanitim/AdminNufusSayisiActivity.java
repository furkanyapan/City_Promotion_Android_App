package com.example.sehirtanitim;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class AdminNufusSayisiActivity extends AppCompatActivity {
    private Button btnEkle, btnSil, btnGuncelle, btnAdminMain;
    private ListView listViewNufusSayisi;

    private ArrayList<String> nufuslar;
    private ArrayList<Tablo> nufusListesi;

    private Veritabani vt;
    private ArrayAdapter<String> adt;
    private int idisi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_nufus_sayisi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnEkle = findViewById(R.id.btnEkle);
        btnSil = findViewById(R.id.btnSil);
        btnGuncelle = findViewById(R.id.btnGuncelle);
        btnAdminMain = findViewById(R.id.btnAdminMain);
        listViewNufusSayisi = findViewById(R.id.listViewNufusSayisi);

        nufuslar = new ArrayList<String>();
        nufusListesi = new ArrayList<Tablo>();
        vt = new Veritabani(this);
        SQLiteDatabase db = vt.getReadableDatabase();
        String sorgu = "SELECT * FROM Tablo";
        Cursor c = db.rawQuery(sorgu, null);
        while (c.moveToNext()){
            nufusListesi.add(new Tablo(c.getInt(0), c.getString(1), c.getInt(2), c.getInt(3), c.getInt(4), c.getString(5)));;
        }
        for (int i=0; i<nufusListesi.size(); i++){
            nufuslar.add(nufusListesi.get(i).getIlce_name() + " " + nufusListesi.get(i).getNufus_2021() + " " + nufusListesi.get(i).getNufus_2022() + " " + nufusListesi.get(i).getNufus_2023());
        }
        adt = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, nufuslar);
        listViewNufusSayisi.setAdapter(adt);
        listViewNufusSayisi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                idisi = nufusListesi.get(position).getNufus_id();
                Toast.makeText(getApplicationContext(),String.valueOf(idisi),Toast.LENGTH_SHORT).show();
            }
        });
        btnEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminNufusSayisiActivity.this, EkleAdminNufusSayisiActivity.class));
            }
        });

        btnSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sayfa=new Intent(getApplicationContext(), SilAdminNufusSayisiActivity.class);
                sayfa.putExtra("idisi",idisi);
                startActivity(sayfa);
            }
        });

        btnGuncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sayfa=new Intent(getApplicationContext(), GuncelleAdminNufusSayisiActivity.class);
                sayfa.putExtra("idisi",idisi);
                startActivity(sayfa);
            }
        });

        btnAdminMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminNufusSayisiActivity.this, AdminMainActivity.class));
            }
        });
    }
}