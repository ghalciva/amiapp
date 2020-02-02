package com.example.amiapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class LawDetail extends AppCompatActivity {
    Button btnCompartir, btnAgree, btnDesagree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law_detail2);

        btnCompartir = findViewById(R.id.btnCompartir);
        btnAgree = findViewById(R.id.btnAgree);
        btnDesagree = findViewById(R.id.btnDesagree);

        String name = getIntent().getExtras().getString("name");
        String detalle = getIntent().getExtras().getString("detalle");
        String resumen = getIntent().getExtras().getString("resumen");
        String exp_articulo = getIntent().getExtras().getString("exp_articulo");

        TextView tv_name = findViewById(R.id.art_nombre);
        TextView tv_detalle = findViewById(R.id.art_detalle);
        TextView tv_descripcion = findViewById(R.id.artDescripcion);

        tv_name.setText(name);
        tv_detalle.setText(detalle);
        tv_descripcion.setText(resumen);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

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
    }

}






