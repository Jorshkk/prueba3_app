package com.example.prueba2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.TimerTask;
import java.util.TooManyListenersException;

public class TecnicoScreen extends AppCompatActivity {
    EditText ingreso_edt_id, edt_tec_estado, edt_tec_comentario;
    TextView ingreso_edt_marca, ingreso_edt_modelo, ingreso_edt_ram, ingreso_edt_so,
            ingreso_edt_rut, ingreso_edt_requerimiento, txt_actualizar;
    Button ingreso_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tecnico_screen);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        ingreso_edt_id = findViewById(R.id.ingreso_edt_id);
        edt_tec_estado = findViewById(R.id.edt_tec_estado);
        edt_tec_estado.setEnabled(false);
        edt_tec_comentario = findViewById(R.id.edt_tec_comentario);
        edt_tec_comentario.setEnabled(false);
        ingreso_edt_marca = findViewById(R.id.ingreso_edt_marca);
        ingreso_edt_modelo = findViewById(R.id.ingreso_edt_modelo);
        ingreso_edt_ram = findViewById(R.id.ingreso_edt_ram);
        ingreso_edt_so = findViewById(R.id.ingreso_edt_so);
        ingreso_edt_rut = findViewById(R.id.ingreso_edt_rut);
        ingreso_edt_requerimiento = findViewById(R.id.ingreso_edt_requerimiento);
        txt_actualizar = findViewById(R.id.txt_actualizar);
        txt_actualizar.setEnabled(false);
        ingreso_btn = findViewById(R.id.ingreso_btn);

    }

    public void modificaEstado(View view) {

        gestorBd gestor = new gestorBd(this, "clinica", null, 1);
        SQLiteDatabase db = gestor.getWritableDatabase();

        String getId = ingreso_edt_id.getText().toString();

        Cursor find_id = db.rawQuery("select marca,modelo,ram,sistema,rut_cliente,requerimiento,estado,comentario from equipos where id ='" + getId + "'", null);

        if (!getId.isEmpty()) {
            if (find_id.moveToFirst()) {
                String verificaEstado = find_id.getString(6);
                if (!verificaEstado.equals("reparado") && !verificaEstado.equals("entregado")) {
                    ingreso_btn.setEnabled(false);
                    ingreso_edt_marca.setText(find_id.getString(0));
                    ingreso_edt_modelo.setText(find_id.getString(1));
                    ingreso_edt_ram.setText(find_id.getString(2));
                    ingreso_edt_so.setText(find_id.getString(3));
                    ingreso_edt_rut.setText(find_id.getString(4));
                    ingreso_edt_requerimiento.setText(find_id.getString(5));
                    edt_tec_estado.setText(find_id.getString(6));
                    edt_tec_comentario.setText(find_id.getString(7));
                    edt_tec_estado.setEnabled(true);
                    edt_tec_comentario.setEnabled(true);
                    ingreso_edt_id.setEnabled(false);
                    txt_actualizar.setEnabled(true);
                    txt_actualizar.setText("Actualizar");
                } else {
                    Toast.makeText(this, "PC fue entregado o ya esta reparado", Toast.LENGTH_SHORT).show();
                    ingreso_edt_id.setText("");
                }
            } else {
                Toast.makeText(this, "ID no registrado aun", Toast.LENGTH_SHORT).show();
                ingreso_edt_marca.setText("");
                ingreso_edt_modelo.setText("");
                ingreso_edt_ram.setText("");
                ingreso_edt_so.setText("");
                ingreso_edt_rut.setText("");
                ingreso_edt_requerimiento.setText("");
                edt_tec_estado.setText("");
                edt_tec_comentario.setText("");
                edt_tec_estado.setEnabled(false);
                edt_tec_comentario.setEnabled(false);
            }
        } else {
            Toast.makeText(this, "Ingrese un ID", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    public void goToHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void actualizarEquipo(View view) {
        gestorBd gestor = new gestorBd(this, "clinica", null, 1);
        SQLiteDatabase db = gestor.getWritableDatabase();

        String estado = edt_tec_estado.getText().toString().toLowerCase();
        String comentario = edt_tec_comentario.getText().toString().toLowerCase();
        String getId = ingreso_edt_id.getText().toString();

        if (!estado.isEmpty()) {
            if (estado.equals("en reparacion") || estado.equals("reparado") || estado.equals("en reparación")) {
                ContentValues row = new ContentValues();
                row.put("estado", estado);
                row.put("comentario", comentario);
                int filas = db.update("equipos", row, "id=" + getId, null);
                if (filas == 1) {
                    Toast.makeText(this, "Actualizado Correctamente", Toast.LENGTH_SHORT).show();
                    ingreso_edt_marca.setText("");
                    ingreso_edt_modelo.setText("");
                    ingreso_edt_ram.setText("");
                    ingreso_edt_so.setText("");
                    ingreso_edt_rut.setText("");
                    ingreso_edt_requerimiento.setText("");
                    edt_tec_estado.setText("");
                    edt_tec_comentario.setText("");
                    edt_tec_estado.setEnabled(false);
                    edt_tec_comentario.setEnabled(false);
                    ingreso_edt_id.setEnabled(true);
                    txt_actualizar.setEnabled(false);
                    txt_actualizar.setText("");
                    ingreso_edt_id.setText("");
                    ingreso_btn.setEnabled(true);
                }
            } else {
                Toast.makeText(this, "ingresar 'en reparacion' o 'reparado'", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No se pudo actualizar", Toast.LENGTH_SHORT).show();
        }
    }

}