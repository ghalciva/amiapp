package com.example.amiapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class RegisterUser extends AppCompatActivity {

    Button createAcc;
    EditText txtIdentityNumber;
    EditText txtCreateNames;
    EditText txtCreateLastNames;
    EditText txtCreateBirthDate;
    EditText txtCreateSex;
    EditText txtCreateEmail;
    EditText txtCreatePass;
    EditText txtCreateConfirmPass;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.registeruser);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        createAcc = findViewById(R.id.btnCreateAcc);
        txtIdentityNumber = findViewById(R.id.txtIdentityNumber);
        txtCreateNames = findViewById(R.id.txtCreateNames);
        txtCreateLastNames = findViewById(R.id.txtCreateLastNames);
        txtCreateBirthDate = findViewById(R.id.txtCreateBirthDate);
        txtCreateSex = findViewById(R.id.txtCreateSex);
        txtCreateEmail = findViewById(R.id.txtCreateEmail);
        txtCreatePass = findViewById(R.id.txtCreatePass);
        txtCreateConfirmPass = findViewById(R.id.txtCreateConfirmPass);

        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Submit();
            }
        });
    }

    private void Submit() {

        String URL = "http://68.66.207.7:3000/api/personas";
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
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
                String nombre = txtCreateNames.getText().toString();
                String apellido = txtCreateLastNames.getText().toString();
                String cedula = txtIdentityNumber.getText().toString();
                String fecha_nacimiento = txtCreateBirthDate.getText().toString();
                String correo = txtCreateEmail.getText().toString();
                String sexo = txtCreateSex.getText().toString();
                headers.put("nombre",nombre);
                headers.put("apellido",apellido);
                headers.put("cedula",cedula);
                headers.put("fecha_nacimiento",fecha_nacimiento);
                headers.put("correo",correo);
                headers.put("sexo",sexo);
                return headers;
            }
        };
        requestQueue.add(stringRequest);
    }
}
