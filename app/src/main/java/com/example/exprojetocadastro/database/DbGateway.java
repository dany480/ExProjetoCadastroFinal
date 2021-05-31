package com.example.exprojetocadastro.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DbGateway {

    private static DbGateway dbGateway;
    private SQLiteDatabase db;

    public static DbGateway getInstance(Context context){
        if (dbGateway == null){
            dbGateway = new DbGateway(context);
        }
        return dbGateway;
    }
    private DbGateway (Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public SQLiteDatabase getDatabase (){
        return db;
    }
}
