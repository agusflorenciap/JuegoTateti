package com.TP1EG.tatetiv4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class DbHelper extends SQLiteOpenHelper {

    Context context;
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME = "TATETI.db";
    public DbHelper(@Nullable Context context, SQLiteDatabase.CursorFactory factory ) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table puntajes(user text primary key,totalGanadas int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE if EXISTS puntajes");
        onCreate(db);
    }

    public boolean buscarYActualizar(String usuario, boolean agregar){
        ContentValues registro= new ContentValues();
        SQLiteDatabase db= this.getWritableDatabase();
        boolean loEncontro=false;
        String q="SELECT totalGanadas FROM puntajes WHERE user="+"'"+usuario+"'";
        Cursor cursor= db.rawQuery(q,null);
        if(cursor.moveToFirst()) {
            if (!agregar){
                int total = cursor.getInt(0);
                total=total+1;
                db.execSQL("UPDATE puntajes SET totalGanadas="+total+" WHERE user="+"'"+usuario+"'");
                db.close();
            }
            loEncontro=true;
        }
        return loEncontro;
    }

    public ArrayList llenarLista(){
        ArrayList<String> lista= new ArrayList<>();
        SQLiteDatabase database= this.getWritableDatabase();
        String q= "SELECT * FROM puntajes";
        Cursor cursor= database.rawQuery(q,null);
        if(cursor.moveToFirst()) {
            do {
                lista.add("    " + cursor.getString(0) + "                             " + cursor.getInt(1) );
            } while (cursor.moveToNext());
        }
        return lista;
    }



}



