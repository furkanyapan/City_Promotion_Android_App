package com.example.sehirtanitim;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Veritabani extends SQLiteOpenHelper {
    public Veritabani(@Nullable Context context) {
        super(context, "Veritabani", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sorgu1 = "CREATE TABLE Tablo1 (nufus_id INTEGER PRIMARY KEY AUTOINCREMENT, ilce_name TEXT, nufus_2021 INTEGER, nufus_2022 INTEGER, nufus_2023 INTEGER, eser_name TEXT)";
        db.execSQL(sorgu1);

        String sorgu2 = "CREATE TABLE Tablo2 (nufus_id INTEGER PRIMARY KEY AUTOINCREMENT, ilce_name TEXT, nufus_2021 INTEGER, nufus_2022 INTEGER, nufus_2023 INTEGER, eser_name TEXT)";
        db.execSQL(sorgu2);

        String sorgu3 = "CREATE TABLE Tablo3 (nufus_id INTEGER PRIMARY KEY AUTOINCREMENT, ilce_name TEXT, nufus_2021 INTEGER, nufus_2022 INTEGER, nufus_2023 INTEGER, eser_name TEXT)";
        db.execSQL(sorgu3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sorgu1 = "DROP TABLE IF EXISTS Tablo1";
        db.execSQL(sorgu1);

        String sorgu2 = "DROP TABLE IF EXISTS Tablo2";
        db.execSQL(sorgu2);

        String sorgu3 = "DROP TABLE IF EXISTS Tablo3";
        db.execSQL(sorgu3);
        onCreate(db);
    }
}
