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


    private static String token = "nada";
    private static final String LOGIN_URL = "https://api.uealecpeterson.net/public/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        String correo = getIntent().getStringExtra("NOMBRE");
        String clave = getIntent().getStringExtra("CLAVE");


        JSONObject params = new JSONObject();
        try {
            params.put("correo", correo);
            params.put("clave", clave);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, LOGIN_URL, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {


                            String tokens = response.getString("access_token");
                            token = tokens;
                            obtenerListaClientes();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        error.printStackTrace();
                    }
                });


        Volley.newRequestQueue(this).add(request);


        //TextView textViewToken = findViewById(R.id.txtLista);
        //textViewToken.setText(token);
    }

    private void obtenerListaClientes() {
        String clientesURL = "https://api.uealecpeterson.net/public/clientes";
        JsonObjectRequest clientesRequest = new JsonObjectRequest(Request.Method.GET, clientesURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray clientesArray = response.getJSONArray("clientes");
                            ArrayList<String> lstClientes = new ArrayList<String> ();

                            for (int i = 0; i < clientesArray.length(); i++) {
                                JSONObject cliente = clientesArray.getJSONObject(i);

                                lstClientes.add( cliente.getString("nombre"));
                                String email = cliente.getString("email");
                                // ...
                                TextView txtv = findViewById(R.id.txtLista);
                                txtv.setText(lstClientes.toString());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        error.printStackTrace();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() {

                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + token);
                return headers;
            }
        };

        // Agregar la solicitud de lista de clientes a la cola de solicitudes de Volley
        Volley.newRequestQueue(this).add(clientesRequest);
    }

}