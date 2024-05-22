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

public class NufusDagilimiActivity extends AppCompatActivity {
    private Button btnMain;
    private ListView listViewNufusDagilimi;

    private ArrayList<String> nufuslar;
    private ArrayList<Tablo> nufusListesi;

    private Veritabani vt;
    private ArrayAdapter<String> adt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nufus_dagilimi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnMain = findViewById(R.id.btnMain);
        listViewNufusDagilimi = findViewById(R.id.listViewNufusDagilimi);

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
            String ilceadi = nufusListesi.get(i).getIlce_name();
            int nufus23 = nufusListesi.get(i).getNufus_2023();
            int toplamnufus = 1273519;
            double oran = ((double) nufus23 / toplamnufus) * 100;
            nufuslar.add(ilceadi + "      " + nufus23 + "      " + "%" + String.format("%.2f", oran));
        }
        adt = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, nufuslar);
        listViewNufusDagilimi.setAdapter(adt);

        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NufusDagilimiActivity.this, MainActivity.class));
            }
        });
    }
}