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
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class NufusSayisiActivity extends AppCompatActivity {
    private Button btnMain;
    private ListView listViewNufusSayisi;

    private ArrayList<String> nufuslar;
    private ArrayList<Tablo> nufusListesi;

    private Veritabani vt;
    private ArrayAdapter<String> adt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nufus_sayisi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnMain = findViewById(R.id.btnMain);
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

        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NufusSayisiActivity.this, MainActivity.class));
            }
        });
    }



}