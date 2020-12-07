package com.example.prueba2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
    }

    public void ingresar(View view){
        Intent intent = new Intent(this,IngresoPc.class);
        startActivity(intent);
    }
    public void verEstado(View view){
        Intent intent = new Intent(this,VerEstado.class);
        startActivity(intent);
    }
    public void tecnico(View view){
        Intent intent = new Intent(this,TecnicoScreen.class);
        startActivity(intent);
    }
    public void eliminar(View view){
        Intent intent = new Intent(this,VerifyLogin.class);
        startActivity(intent);
    }
    public void entrega(View view){
        Intent intent = new Intent(this,Entrega.class);
        startActivity(intent);
    }
}