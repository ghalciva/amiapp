package com.example.amiapp;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class LawOne extends AppCompatActivity {

    Button btnReadArticles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lawone);

        final String id =  getIntent().getExtras().getString("_id");

        Log.i("law one", "idley "+id);

        final String name = getIntent().getExtras().getString("nombre");
        String descripcion = getIntent().getExtras().getString("descripcion");
        String cod_decreto = getIntent().getExtras().getString("cod_decreto");
        String fecha_publicacion = getIntent().getExtras().getString("fecha_publicacion");
        String proponente = getIntent().getExtras().getString("proponente");

        TextView tv_name = findViewById(R.id.ley_nombre);
        TextView tv_description = findViewById(R.id.ley_descripcion);
        TextView tv_cod_decreto = findViewById(R.id.cod_decreto);
        TextView tv_fecha_publicacion = findViewById(R.id.fecha_publicacion);
        TextView tv_proponente = findViewById(R.id.proponente);

        tv_name.setText(name);
        tv_description.setText(descripcion);
        tv_cod_decreto.setText(cod_decreto);
        tv_fecha_publicacion.setText(fecha_publicacion);
        tv_proponente.setText(proponente);

        btnReadArticles = findViewById(R.id.btnArticulos);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        btnReadArticles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LawOne.this, HomeArticle.class);
                intent.putExtra("idley", id);
                intent.putExtra("nombreley", name);
                startActivity(intent);
            }
        });
    }

}
