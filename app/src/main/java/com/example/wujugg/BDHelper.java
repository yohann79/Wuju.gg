package com.example.wujugg;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDHelper extends SQLiteOpenHelper {

    public BDHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String reqChampion = "create table champion (idChampion INTEGER  PRIMARY KEY autoincrement ,nom text,description text, competenceA text,competenceZ text, competenceE text,competenceR text, passif text, idRole INTEGER, foreign key (idRole) references role (idRole))";
        String reqRole = "create table role (idRole INTEGER  PRIMARY KEY autoincrement,nom text, description text)";
        db.execSQL(reqChampion);
        db.execSQL(reqRole);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
