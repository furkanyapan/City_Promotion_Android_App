package com.example.sehirtanitim;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class TarihiEserlerActivity extends AppCompatActivity {
    private Button btnMain;
    private ListView listViewTarihiEser;

    private ArrayList<String> eserler;
    private ArrayList<Tablo> eserListesi;

    private Veritabani vt;
    private ArrayAdapter<String> adt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tarihi_eserler);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnMain = findViewById(R.id.btnMain);
        listViewTarihiEser = findViewById(R.id.listViewTarihiEser);

        eserler = new ArrayList<String>();
        eserListesi = new ArrayList<Tablo>();
        vt = new Veritabani(this);
        SQLiteDatabase db = vt.getReadableDatabase();
        String sorgu = "SELECT * FROM Tablo";
        Cursor c = db.rawQuery(sorgu, null);
        while (c.moveToNext()){
            eserListesi.add(new Tablo(c.getInt(0), c.getString(1), c.getInt(2), c.getInt(3), c.getInt(4), c.getString(5)));;
        }
        for (int i=0; i<eserListesi.size(); i++){
            if(eserListesi.get(i).getEser_name() != null)
                eserler.add(eserListesi.get(i).getIlce_name() + " " + eserListesi.get(i).getEser_name());
        }
        adt = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, eserler);
        listViewTarihiEser.setAdapter(adt);

        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TarihiEserlerActivity.this, MainActivity.class));
            }
        });
    }
}