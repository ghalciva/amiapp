package com.example.amiapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class LawDetail extends AppCompatActivity {
    Button btnCompartir, btnAgree, btnDesagree;
    TextView getTitleLaw1;
    TextView getContentLaw1;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law_detail2);

        btnCompartir = findViewById(R.id.btnCompartir);
        btnAgree = findViewById(R.id.btnAgree);
        btnDesagree = findViewById(R.id.btnDesagree);
        getContentLaw1 = findViewById(R.id.getContentLaw1);
        getTitleLaw1 = findViewById(R.id.getTitleLaw1);

        requestQueue = Volley.newRequestQueue(this);

        getData();

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnCompartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LawDetail.this);
                builder.setMessage("")
                        .setTitle("Compartir en: ")
                        .setCancelable(false)
                        .setNeutralButton("Aceptar",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        btnAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Gracias por votar",
                        Toast.LENGTH_SHORT);

                toast.show();
                btnDesagree.setEnabled(false);
                Intent intent = new Intent(LawDetail.this, Resume.class);
                startActivity(intent);
            }
        });

        btnDesagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Gracias por votar",
                        Toast.LENGTH_SHORT);

                toast.show();
                btnAgree.setEnabled(false);
                Intent intent = new Intent(LawDetail.this, Resume.class);
                startActivity(intent);
            }
        });
        getData();
    }

    private void getData(){
        String url = "http://68.66.207.7:3000/api/ley";


    }

}






