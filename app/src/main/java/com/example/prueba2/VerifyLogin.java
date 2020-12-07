package com.example.prueba2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class VerifyLogin extends AppCompatActivity {
    EditText login_edt_user,login_edt_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_login);

        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        login_edt_user = findViewById(R.id.login_edt_user);
        login_edt_password = findViewById(R.id.login_edt_password);
    }
    public void validarLogin(View view){
        String user = login_edt_user.getText().toString();
        String password = login_edt_password.getText().toString();

        if(!user.isEmpty() && !password.isEmpty()){
            if(!user.equals("leicaman@inacap.cl") && !password.equals("superleicaman")){
                Toast.makeText(this, "Quizas no deberia estar aqui..", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(this,DeleteEquipo.class);
                startActivity(intent);
            }
        }else{
            Toast.makeText(this, "Ingrese usuario y contraseña", Toast.LENGTH_SHORT).show();
        }

    }
}