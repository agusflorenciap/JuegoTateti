package com.TP1EG.tatetiv4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnUnJugador;
    Button btnDsJugadores;
    Button btnSalir;
    Boolean btnElegido=false;
    Button btnPunt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnUnJugador=(Button) findViewById(R.id.buttonUnJugador);
        btnUnJugador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrir();
            }
        });
        btnDsJugadores=(Button) findViewById(R.id.button2Jugadores);
        btnDsJugadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnElegido=true;
                abrir();
            }
        });
        btnPunt=(Button) findViewById(R.id.buttonListado);
        btnPunt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listado();
            }
        });

        btnSalir=(Button) findViewById(R.id.buttonSalirPrincipal);
        btnSalir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void abrir () {
        Intent i = new Intent(this, Juego.class);
        i.putExtra("Eleccion", btnElegido);
        startActivity(i);
    }

    private void listado(){
        Intent i = new Intent(this, Puntajes.class);
        startActivity(i);
    }
}