package com.example.amiapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

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

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LawDetail extends AppCompatActivity {
    Button btnCompartir, btnAgree, btnDesagree, btnComentario;
    TextView txtComentario;
    TextView comentarioPublicado;
    RequestQueue requestQueue;
    ArrayAdapter<String> myAdapter;
    private final String URL = "http://10.0.2.2:3000/api/comentarios";
    int counter1 = 0;
    int counter2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law_detail2);

        btnCompartir = findViewById(R.id.btnCompartir);
        btnAgree = findViewById(R.id.btnAgree);
        btnDesagree = findViewById(R.id.btnDesagree);
        btnComentario = findViewById(R.id.btnComentario);
        txtComentario = findViewById(R.id.comment);
        comentarioPublicado  = findViewById(R.id.comentarioPublicado);

        final String name = getIntent().getExtras().getString("name");
        final String detalle = getIntent().getExtras().getString("detalle");
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



                String urlT = "http://www.twitter.com/intent/tweet?url=http://localhost:3000/api/ley&text="+name+detalle;
                //String urlF = "https://www.facebook.com/sharer/sharer.php?u=http://localhost:3000/api/ley&text="+name+detalle;
                Intent i = new Intent(Intent.ACTION_VIEW);
                //Intent f = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(urlT));
                //f.setData(Uri.parse(urlF));
                startActivity(i);
                //startActivity(f);
            }
        });

        btnAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter1 = counter1+1;
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Gracias por votar",
                        Toast.LENGTH_SHORT);

                toast.show();
                btnDesagree.setEnabled(false);
            }
        });

        btnDesagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter2 = counter2+1;
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Gracias por votar",
                        Toast.LENGTH_SHORT);

                toast.show();
                btnAgree.setEnabled(false);
            }
        });

        btnComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                publicarComentario();
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Comentario publicado",
                        Toast.LENGTH_SHORT);

                toast.show();
                btnAgree.setEnabled(false);

            }
        });

    }

    private void publicarComentario(){

        //PUBLICAR COMENTARIO EN TEXTVIEW
        String comentario = txtComentario.getText().toString();
        comentarioPublicado.setText(comentario);
        txtComentario.setEnabled(false);

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST, URL,
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
                String likes = String.valueOf(counter1);
                String dislikes = String.valueOf(counter2);
                String comentario = txtComentario.getText().toString();
                headers.put("like",likes);
                headers.put("dislike",dislikes);
                headers.put("comentarios",comentario);
                return headers;
            }
        };
        requestQueue.add(stringRequest);
    }

}






