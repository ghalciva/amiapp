package com.example.amiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    TextView txtForgotPass;
    TextView txtRegister;
    Button btnLogin;
    TextView txtid;
    TextView txtPass;
    private JsonArrayRequest request;
    private static final String URL = "http://68.66.207.7:3000/api/ciudadano";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtForgotPass = findViewById(R.id.txtForgotPass);
        txtRegister = findViewById(R.id.txtRegister);
        btnLogin = findViewById(R.id.btnLogin);
        txtid = findViewById(R.id.txtid);
        txtPass = findViewById(R.id.txtPass);

        txtForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ForgotPass.class);
                startActivity(intent);
            }
        });

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterUser.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session();
            }
        });

        getSupportActionBar().hide();

        txtid.setError(null);
        txtPass.setError(null);
    }


    private void session(){

        //validar inicio de sesion, extraer datos de api
        /*request = new JsonArrayRequest (URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;

                for (int i =0; i < response.length(); i++){
                    try {
                        jsonObject = response.getJSONObject(i);

                        String idc = jsonObject.getString("cedula").trim();
                        String passc = jsonObject.getString("contrasena").trim();

                        Map<String, String> params = new HashMap<>();
                        params.put("cedula", idc);
                        params.put("constrasena", passc);

                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
*/

        String cedula = txtid.getText().toString();
        String password = txtPass.getText().toString();

        /*Bandera evidenciar algun error durante la validación de los datos
        Variable para contener el campo a ser enfocado*/
        boolean cancel = false;
        View focusView = null;

        //Comprobar si el campo para el cedula esta vacio.
        if (TextUtils.isEmpty(cedula)) {
            /**Envia el error a la caja de Texto*/
            txtid.setError("");
            txtid.setError("Ingrese número cédula");
            focusView = txtid;
            cancel = true;
        }

        //Comprobar si el password ingresado no es nulo y es valido
        if (TextUtils.isEmpty(password)) {
            /**Envia el error a la caja de Texto*/
            txtPass.setError("");
            txtPass.setError("Ingrese contraseña");
            focusView = txtPass;
            cancel = true;
        }
        if (!TextUtils.isEmpty(cedula) && !TextUtils.isEmpty(password)){
            Intent intent = new Intent(MainActivity.this, HomeLaw.class);
            startActivity(intent);
        }

    }


    /*Comprobar si la contraseña ingresada cumple con restricciones establecidas
    private boolean isIdValid(String id) {
        /**Si la cadena supera los 4 caracteres es una contraseña valida
        return id.length() ==10 ;
    }*/



}
