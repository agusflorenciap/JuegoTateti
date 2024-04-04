package com.TP1EG.tatetiv4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Puntajes extends AppCompatActivity {
    Button btnSalir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntajes);
        btnSalir=findViewById(R.id.buttonSalir2);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ListView lv = (ListView) findViewById(R.id.listaPunt);
        ArrayList<Puntajes> list;
        DbHelper admin= new DbHelper(this, null);
        list=admin.llenarLista();
        ArrayAdapter adaptador=new ArrayAdapter(this, R.layout.layout_listview, list);
        lv.setAdapter(adaptador);




    }
}