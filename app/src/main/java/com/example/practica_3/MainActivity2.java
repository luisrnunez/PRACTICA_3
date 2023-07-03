package com.example.practica_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {


    private static String token;
    private static final String LOGIN_URL = "https://api.uealecpeterson.net/public/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Obtén los valores del correo y la clave pasados desde la actividad principal
        String correo = getIntent().getStringExtra("NOMBRE");
        String clave = getIntent().getStringExtra("APELLIDO");

        // Crear el objeto JSON con los parámetros de la solicitud
        JSONObject params = new JSONObject();
        try {
            params.put("correo", correo);
            params.put("clave", clave);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Crear la solicitud POST con Volley
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, LOGIN_URL, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Manejar la respuesta del servidor
                        try {
                            // Obtener el token de la respuesta


                            String token = response.getString("access_token");


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejar el error de la solicitud
                        error.printStackTrace();
                    }
                });

        // Agregar la solicitud a la cola de solicitudes de Volley
        Volley.newRequestQueue(this).add(request);

        // En el onResponse, cuando obtengas el token, puedes mostrarlo en un TextView, por ejemplo
        TextView textViewToken = findViewById(R.id.txtLista);
        textViewToken.setText(token);
    }

}