package com.example.prueba2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteEquipo extends AppCompatActivity {
EditText ver_edt_id;
    TextView txt_verEstado,txt_comentarios,txt_delete_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_equipo);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        ver_edt_id=findViewById(R.id.ver_edt_id);
        txt_verEstado = findViewById(R.id.txt_verEstado);
        txt_comentarios = findViewById(R.id.txt_comentarios);
        txt_delete_back = findViewById(R.id.txt_delete_back);
    }
    public void eliminarPc(View view){
        gestorBd gestor = new gestorBd(this,"clinica",null,1);
        SQLiteDatabase db = gestor.getWritableDatabase();
        String getId = ver_edt_id.getText().toString();
        if(!getId.isEmpty()){
            int filas = db.delete("equipos","id='"+getId+"'",null);
            if (filas == 1){
                db.close();
                Toast.makeText(this,"Equipo eliminado",Toast.LENGTH_SHORT).show();
                ver_edt_id.setText("");
            }else{
                Toast.makeText(this,"Error Inesperado",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this,"No se reconoce ID",Toast.LENGTH_SHORT).show();
        }
    db.close();
    }
    public void goToHome(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}