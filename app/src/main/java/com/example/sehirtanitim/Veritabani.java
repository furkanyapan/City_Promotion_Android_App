package com.example.sehirtanitim;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Veritabani extends SQLiteOpenHelper {
    public Veritabani(@Nullable Context context) {
        super(context, "Veritabani", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sorgu = "CREATE TABLE Tablo (nufus_id INTEGER PRIMARY KEY AUTOINCREMENT, ilce_name TEXT, nufus_2021 INTEGER, nufus_2022 INTEGER, nufus_2023 INTEGER, eser_name TEXT)";
        db.execSQL(sorgu);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sorgu = "DROP TABLE IF EXISTS Tablo";
        db.execSQL(sorgu);
        onCreate(db);
    }
}
