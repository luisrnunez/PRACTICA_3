package com.example.practica_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONException;

import WebServices.Asynchtask;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void enviaDatos(View view){
        EditText txtNombre = (EditText) findViewById(R.id.txtNombre);
        EditText txtApelllido = (EditText) findViewById(R.id.txtApellido);

        Intent intent = new Intent(MainActivity.this, MainActivity2.class);

        Bundle b = new Bundle();

        b.putString("NOMBRE", txtNombre.getText().toString());
        b.putString("CLAVE", txtApelllido.getText().toString());
        intent.putExtras(b);
        startActivity(intent);


    }

    @Override
    public void processFinish(String result) throws JSONException {

    }
}