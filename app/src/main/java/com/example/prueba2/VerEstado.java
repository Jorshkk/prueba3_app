package com.example.prueba2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class VerEstado extends AppCompatActivity {
    EditText ver_edt_id;
    TextView txt_verEstado,txt_comentarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_estado);

        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        ver_edt_id = findViewById(R.id.ver_edt_id);
        txt_verEstado = findViewById(R.id.txt_verEstado);
        txt_comentarios = findViewById(R.id.txt_comentarios);
    }
    @SuppressLint("ResourceAsColor")
    public void verId(View view){

        gestorBd gestor = new gestorBd(this,"clinica",null,1);
        SQLiteDatabase db = gestor.getWritableDatabase();

        String id = ver_edt_id.getText().toString();

        Cursor find_id = db.rawQuery("select estado,comentario from equipos where id='"+id+"'",null);

        if(id.isEmpty()){
            Toast.makeText(this,"Ingrese un ID",Toast.LENGTH_SHORT).show();
        }else if(!find_id.moveToFirst()){
            Toast.makeText(this,"Ingrese un ID Valido",Toast.LENGTH_SHORT).show();
            ver_edt_id.setText("");
            txt_verEstado.setTextColor(R.color.color_secondarytext);
            txt_verEstado.setText("Estado");
            txt_comentarios.setTextColor(R.color.color_secondarytext);
            txt_comentarios.setText("Comentarios");
        }else{
            find_id.moveToFirst();
            txt_verEstado.setText(find_id.getString(0).toString());
            txt_verEstado.setTextColor(R.color.teal_700);
            txt_comentarios.setText(find_id.getString(1).toString());
            txt_comentarios.setTextColor(R.color.teal_700);
            db.close();

            ver_edt_id.setText("");
        }
        db.close();
    }
    public void home(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}