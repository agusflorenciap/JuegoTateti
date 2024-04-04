package com.TP1EG.tatetiv4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SumarPuntaje extends AppCompatActivity {

    android.widget.Button btnnConfirmar;
    private EditText userText;
    TextView noExiste;
    View view;
    Button btnAgregar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sumar_puntaje);

        userText= (EditText) findViewById(R.id.userName);

        noExiste=(TextView) findViewById(R.id.userNoExiste);
        noExiste.setVisibility(View.INVISIBLE);

        btnnConfirmar=(Button) findViewById((R.id.buttonConfirmar));
        btnnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarPuntaje();
            }
        });

        btnAgregar=(Button) findViewById((R.id.buttonAgregar));
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarUsuario();
            }
        });
    }

    public void guardarPuntaje() {
        DbHelper admin= new DbHelper(this, null);
        SQLiteDatabase db= admin.getWritableDatabase();
        boolean agregar=false;
        String usuario= userText.getText().toString();

        if(!usuario.isEmpty()){
            boolean loEncontro=admin.buscarYActualizar(usuario, agregar);

            if(loEncontro){
                Toast.makeText(this, "Puntaje actualizado",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
            }else{
                noExiste.setVisibility(View.VISIBLE);
            }
        }else
            Toast.makeText(this, "Debe ingresar un nombre de usuario",Toast.LENGTH_SHORT).show();
    }

    public void agregarUsuario(){
        DbHelper admin= new DbHelper(this, null);
        SQLiteDatabase db= admin.getWritableDatabase();

        String usuario= userText.getText().toString();
        boolean agregar=true;
        boolean loEncontro;

        if(!usuario.isEmpty()){
            loEncontro=admin.buscarYActualizar(usuario, agregar);
            if(loEncontro){
                Toast.makeText(this, "El usuario ingresado ya existe, presione CONFIRMAR para actualizar",Toast.LENGTH_SHORT).show();
            }else{
                ContentValues registro= new ContentValues();
                registro.put("user", usuario);
                registro.put("totalGanadas", 1);
                db.insert("puntajes", null, registro);
                db.close();
                Toast.makeText(this, "Usuario agregado",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
            }
        }else
        Toast.makeText(this, "Debe ingresar un nombre de usuario",Toast.LENGTH_SHORT).show();

    }
}