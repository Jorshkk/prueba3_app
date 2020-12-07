package com.example.prueba2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class IngresoPc extends AppCompatActivity {
    EditText ingreso_edt_id,ingreso_edt_marca,ingreso_edt_modelo,ingreso_edt_ram,ingreso_edt_so,
            ingreso_edt_rut,ingreso_edt_requerimiento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_pc);
        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();

            ingreso_edt_id = findViewById(R.id.ingreso_edt_id);
            ingreso_edt_marca = findViewById(R.id.ingreso_edt_marca);
            ingreso_edt_modelo = findViewById(R.id.ingreso_edt_modelo);
            ingreso_edt_ram = findViewById(R.id.ingreso_edt_ram);
            ingreso_edt_so = findViewById(R.id.ingreso_edt_so);
            ingreso_edt_rut = findViewById(R.id.ingreso_edt_rut);
            ingreso_edt_requerimiento = findViewById(R.id.ingreso_edt_requerimiento);
        }
    }
    public void ingresarPc(View view){
        gestorBd gestorBd = new gestorBd(this,"clinica",null,1);
        SQLiteDatabase db = gestorBd.getWritableDatabase();

        String getId = ingreso_edt_id.getText().toString();
        String getMarca = ingreso_edt_marca.getText().toString();
        String getModelo = ingreso_edt_modelo.getText().toString();
        String getRam = ingreso_edt_ram.getText().toString();
        String getSo = ingreso_edt_so.getText().toString();
        String getRut = ingreso_edt_rut.getText().toString();
        String getEstado = "ingresado";
        String getRequerimiento = ingreso_edt_requerimiento.getText().toString();

        Cursor verificarID = db.rawQuery("select id from equipos where id='"+getId+"'",null);

        if(getId.isEmpty() && getMarca.isEmpty() && getModelo.isEmpty() && getRam.isEmpty() &&
                getSo.isEmpty() && getRut.isEmpty() && getEstado.isEmpty() && getRequerimiento.isEmpty()){
            Toast.makeText(this,"Rellene el formulario",Toast.LENGTH_SHORT).show();
        }else if(getId.isEmpty()){
            Toast.makeText(this,"Ingrese un ID",Toast.LENGTH_SHORT).show();
        }else if(getMarca.isEmpty()){
            Toast.makeText(this,"Ingrese una Marca",Toast.LENGTH_SHORT).show();
        }else if(getModelo.isEmpty()){
            Toast.makeText(this,"Ingrese un Modelo",Toast.LENGTH_SHORT).show();
        }else if(getRam.isEmpty()){
            Toast.makeText(this,"Ingrese Cantidad Ram",Toast.LENGTH_SHORT).show();
        }else if(getSo.isEmpty()){
            Toast.makeText(this,"Ingrese un SO",Toast.LENGTH_SHORT).show();
        }else if(getRut.isEmpty()){
            Toast.makeText(this,"Ingrese un Rut",Toast.LENGTH_SHORT).show();
        }else if(getRequerimiento.isEmpty()){
            Toast.makeText(this,"Ingrese un Requerimiento",Toast.LENGTH_SHORT).show();
        }else if(verificarID.moveToFirst()){
            Toast.makeText(this,"ID ingresado esta en uso",Toast.LENGTH_SHORT).show();
        }else{
            ContentValues row = new ContentValues();

            row.put("id",getId);
            row.put("marca",getMarca);
            row.put("modelo",getModelo);
            row.put("ram",getRam);
            row.put("sistema",getSo);
            row.put("rut_cliente",getRut);
            row.put("estado",getEstado);
            row.put("requerimiento",getRequerimiento);
            row.put("comentario","sin comentarios");

            db.insert("equipos",null,row);
            db.close();

            ingreso_edt_id.setText("");
            ingreso_edt_marca.setText("");
            ingreso_edt_modelo.setText("");
            ingreso_edt_ram.setText("");
            ingreso_edt_so.setText("");
            ingreso_edt_rut.setText("");
            ingreso_edt_requerimiento.setText("");

            Toast.makeText(this,"PC Ingresado Correctamente!",Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
    public void home(View view){
        Intent intent = new Intent(this, VerEstado.class);
        startActivity(intent);
    }
    public void menu(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}