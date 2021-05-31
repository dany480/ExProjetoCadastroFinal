package com.example.exprojetocadastro.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.exprojetocadastro.database.contract.EventoContract;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db.evento";
    private static final int DATABASE_VERSION = 3;

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Fazer novas tabelas. Uma para cada exec.
    @Override
    public void onCreate(SQLiteDatabase db) {

       db.execSQL(EventoContract.criarTabela());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(EventoContract.removerTabela());
        db.execSQL(EventoContract.criarTabela());
    }
}
