package com.example.amiapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RedesSociales extends AppCompatActivity {

    Spinner spinner;
    ArrayAdapter<String> myAdapter;
    EditText txtCuenta;
    Button btnSend;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redes);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtCuenta = findViewById(R.id.txtCuenta);
        btnSend = findViewById(R.id.btnSend);

        Bundle bundle = getIntent().getExtras();
        final String id = bundle.getString("_id").toString();

        spinner = findViewById(R.id.txtAccs);
        myAdapter = new ArrayAdapter<String>(RedesSociales.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.accs));
        myAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(myAdapter);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviar(id);
            }
        });

    }

    private void enviar(final String id){

        //conectarse bd
        //conectarse con la bd y enviar datos
        String URL = "http://10.0.2.2:3000/api/redsocial";
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), HomeLaw.class);
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        )
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> headers = new HashMap<String, String>();
                String nombre = txtCuenta.getText().toString();
                String cuenta = spinner.getSelectedItem().toString();
                headers.put("nombre",nombre);
                headers.put("cuenta",cuenta);
                headers.put("_id", id);
                return headers;
            }
        };
        requestQueue.add(stringRequest);

    }
}