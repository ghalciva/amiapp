package com.example.amiapp;

import android.os.Bundle;
import android.util.Log;
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
                //validar numero cedula ecuatoriana
                String cedula = txtIdentityNumber.getText().toString();

                if (cedula.length()==10){

                    int digito_region = Integer.parseInt(cedula.substring(0,2));
                    if (digito_region >= 1 && digito_region <= 24){
                        int ultimo_digito = Integer.parseInt(cedula.substring(9,10));
                        int pares = Integer.parseInt(cedula.substring(1,2)) + Integer.parseInt(cedula.substring(3,4)) + Integer.parseInt(cedula.substring(5,6)) + Integer.parseInt(cedula.substring(7,8));
                        int numero1 = Integer.parseInt(cedula.substring(0,1));

                        numero1 = (numero1 *2);
                        if (numero1 > 9){
                            numero1 = (numero1 - 9);
                        }

                        int numero3 = Integer.parseInt(cedula.substring(2,3));
                        numero3 = (numero3 *2);
                        if (numero3 > 9){
                            numero3 = (numero3 - 9);
                        }

                        int numero5 = Integer.parseInt(cedula.substring(4,5));
                        numero5 = (numero5 *2);
                        if (numero5 > 9){
                            numero5 = (numero5 - 9);
                        }

                        int numero7 = Integer.parseInt(cedula.substring(6,7));
                        numero7 = (numero7 *2);
                        if (numero7 > 9){
                            numero7 = (numero7 - 9);
                        }

                        int numero9 = Integer.parseInt(cedula.substring(8,9));
                        numero9 = (numero9 *2);
                        if (numero9 > 9){
                            numero9 = (numero9 - 9);
                        }

                        int impares = numero1 + numero3 + numero5 + numero7 + numero9;
                        int suma_total = (pares + impares);
                        String primerdigito_suma = (String.valueOf(suma_total)).substring(0,1);
                        int decena = (Integer.parseInt(primerdigito_suma)+1)*10;
                        int digito_validador = decena - suma_total;
                        if (digito_validador == 10){
                            digito_validador = 0;
                        }
                        if (digito_validador == ultimo_digito){
                            Log.d("validar","Número de cédula válido");
                            Enviar();
                        }else {
                            Log.d("validar","Número de cédula inválido");
                            Toast.makeText(getApplicationContext(),"Número de cédula no válido",Toast.LENGTH_LONG).show();
                        }
                    }
                }

            }
        });
    }

    private void ValidarCamposVacios() {

        //validar textos completo
        if (txtIdentityNumber.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Campo de cédula vacío", Toast.LENGTH_LONG).show();
        }else {
            if (txtCreateNames.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(), "Campo de nombre vacío", Toast.LENGTH_LONG).show();
            }else {
                if (txtCreateLastNames.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Campo de apellido vacío", Toast.LENGTH_LONG).show();
                }else {
                    if (txtCreatePass.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Campo de contraseña vacío", Toast.LENGTH_LONG).show();
                    }else {
                        if (txtCreateBirthDate.getText().toString().isEmpty()){
                            Toast.makeText(getApplicationContext(), "Campo de fecha de nacimiento vacío", Toast.LENGTH_LONG).show();
                        }else{
                            if (txtCreateSex.getText().toString().isEmpty()){
                                Toast.makeText(getApplicationContext(), "Campo de género vacío", Toast.LENGTH_LONG).show();
                            }else{
                                if (txtCreateEmail.getText().toString().isEmpty()){
                                    Toast.makeText(getApplicationContext(), "Campo de correo vacío", Toast.LENGTH_LONG).show();
                                }else{
                                    if (txtCreateConfirmPass.getText().toString().isEmpty()){
                                        Toast.makeText(getApplicationContext(), "Campo de contraseña vacío", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        ValidarContrasena();

    }

    private void Enviar() {
        ValidarCamposVacios();

        //conectarse con la bd y enviar datos
        String URL = "http://68.66.207.7:3000/api/ciudadano";
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
                String contrasena = txtCreatePass.getText().toString();
                //String fecha_nacimiento = txtCreateBirthDate.getText().toString();
                //String correo = txtCreateEmail.getText().toString();
                //String sexo = txtCreateSex.getText().toString();
                headers.put("nombre",nombre);
                headers.put("apellido",apellido);
                headers.put("cedula",cedula);
                headers.put("contrasena", contrasena);
                //headers.put("fecha_nacimiento",fecha_nacimiento);
                //headers.put("correo",correo);
                //headers.put("sexo",sexo);
                return headers;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void ValidarContrasena(){
        if (txtCreateConfirmPass.getText().toString() != txtCreatePass.getText().toString()){
            Toast.makeText(getApplicationContext(), "La contraseña no es la misma", Toast.LENGTH_LONG).show();
        }
    }
}
