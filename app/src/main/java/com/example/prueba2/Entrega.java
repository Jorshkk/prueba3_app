package com.example.prueba2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Entrega extends AppCompatActivity {
    EditText edt_entrega_id,edt_entrega_estado;
    TextView txt_cambiarEstado,txt_aceptar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrega);

        if (getSupportActionBar()!= null){
            getSupportActionBar().hide();
        }
        edt_entrega_id = findViewById(R.id.edt_entrega_id);
        edt_entrega_estado = findViewById(R.id.edt_entrega_estado);edt_entrega_estado.setEnabled(false);
        txt_cambiarEstado = findViewById(R.id.txt_cambiarEstado);txt_cambiarEstado.setEnabled(false);
        txt_aceptar = findViewById(R.id.txt_aceptar);

    }

    public void cambiarEstado(View view){
        gestorBd  gestor = new gestorBd(this,"clinica",null,1);
        SQLiteDatabase db = gestor.getWritableDatabase();
        String id = edt_entrega_id.getText().toString();
        Cursor find_id = db.rawQuery("select estado from equipos where id ='"+id+"'",null);

        if (!id.isEmpty()){
            if (find_id.moveToFirst()){
                String reparado = find_id.getString(0);
                    if (reparado.equals("reparado")){
                        edt_entrega_estado.setEnabled(true);
                        edt_entrega_estado.setText(find_id.getString(0));
                        txt_cambiarEstado.setEnabled(true);
                        txt_aceptar.setText("ID encontrado");
                        txt_aceptar.setEnabled(false);
                        txt_cambiarEstado.setText("Actualizar");
                        edt_entrega_id.setEnabled(false);
                    }else{
                        Toast.makeText(this, "El equipo aun no está Reparado", Toast.LENGTH_SHORT).show();
                    }
            }else{
                Toast.makeText(this, "ID "+id+" aun no está ingresado", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Ingrese un ID", Toast.LENGTH_SHORT).show();
        }
    }
    public void actualizar(View view){
        gestorBd  gestor = new gestorBd(this,"clinica",null,1);
        SQLiteDatabase db = gestor.getWritableDatabase();
        String estado = edt_entrega_estado.getText().toString().toLowerCase();
        String getId = edt_entrega_id.getText().toString();

        if(estado.equals("entregado")){
            ContentValues row = new ContentValues();
            row.put("estado",estado);
            int filas = db.update("equipos",row,"id="+getId, null);
            if(filas == 1){
                txt_aceptar.setEnabled(true);
                edt_entrega_estado.setText("");
                edt_entrega_id.setText("");
                txt_cambiarEstado.setText("");
                txt_aceptar.setText("Aceptar");
                txt_cambiarEstado.setEnabled(false);
                edt_entrega_id.setEnabled(true);
                edt_entrega_estado.setEnabled(false);
                Toast.makeText(this,"Actualizado Correctamente",Toast.LENGTH_SHORT).show();
            }else{

            }
        }else{
            Toast.makeText(this, "Estado solo puede ser ENTREGADO", Toast.LENGTH_SHORT).show();
        }

    }
    public void goToHome(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}