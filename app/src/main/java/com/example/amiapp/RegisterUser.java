package com.example.amiapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class RegisterUser extends AppCompatActivity {

    Button createAcc;
    EditText txtIdentityNumber;
    EditText txtCreateNames;
    EditText txtCreateLastNames;
    EditText txtCreateBirthDate;
    Spinner spinner;
    EditText txtCreateEmail;
    EditText txtCreatePass;
    EditText txtCreateConfirmPass;
    RequestQueue requestQueue;
    DatePickerDialog.OnDateSetListener setListener;
    ArrayAdapter<String> myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.registeruser);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        createAcc = findViewById(R.id.btnCreate);
        txtIdentityNumber = findViewById(R.id.txtIdentityNumber);
        txtCreateNames = findViewById(R.id.txtCreateNames);
        txtCreateLastNames = findViewById(R.id.txtCreateLastNames);
        spinner = findViewById(R.id.txtCreateSex);
        txtCreateEmail = findViewById(R.id.txtCreateEmail);
        txtCreatePass = findViewById(R.id.txtCreatePass);
        txtCreateConfirmPass = findViewById(R.id.txtCreateConfirmPass);
        txtCreateBirthDate = findViewById(R.id.txtCreateBirthDate);

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
                            ValidarCamposVacios();
                        }else {
                            Log.d("validar","Número de cédula inválido");
                            Toast.makeText(getApplicationContext(),"Número de cédula no válido",Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });

        txtCreateBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(RegisterUser.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        setListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                Log.d("tag", "date: " + i + "/" + i1 + "/" + i2);
                txtCreateBirthDate.setText(i+"/"+(i1+1)+"/"+i2);
            }
        };

        myAdapter = new ArrayAdapter<String>(RegisterUser.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.genre));
        myAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(myAdapter);

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
                            if (spinner.toString().isEmpty()){
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

        //conectarse con la bd y enviar datos
        String URL = "http://10.0.2.2:3000/api/ciudadano";
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RegisterUser.this, RedesSociales.class);
                        //intent.putExtra("_id", );
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
                String nombre = txtCreateNames.getText().toString();
                String apellido = txtCreateLastNames.getText().toString();
                String cedula = txtIdentityNumber.getText().toString();
                String contrasena = txtCreatePass.getText().toString();
                headers.put("nombre",nombre);
                headers.put("apellido",apellido);
                headers.put("cedula",cedula);
                headers.put("contrasena", contrasena);
                return headers;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void ValidarContrasena(){

        if ((txtCreateConfirmPass.getText().toString()) != (txtCreatePass.getText().toString())){
            Enviar();
        }else{
            Toast.makeText(getApplicationContext(), "La contraseña no es la misma", Toast.LENGTH_LONG).show();
        }
    }
}
