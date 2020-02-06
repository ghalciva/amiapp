package com.example.amiapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeArticle extends AppCompatActivity {

    private final String JSON_URL = "http://10.0.2.2:3000/api/articulos";
    //private final String JSON_URL = "http://68.66.207.7:3000/api/articulos";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<Articles> listArticle;
    private RecyclerView recyclerView;
    TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_articles);

        //Bundle bundle = getIntent().getExtras();
        //final String number = bundle.getString("phone").toString();

        message = findViewById(R.id.textView3);

        String idley =  getIntent().getExtras().getString("idley");
        String idnombre = getIntent().getExtras().getString("nombreley");
        listArticle = new ArrayList<>();

        Log.i("de law one",idley + idnombre);

        cargarArticulos(idley);

        message.setText("Artículos de "+idnombre);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
  }

  public void cargarArticulos(final String idley){

        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;
                for (int i =0; i < response.length(); i++){
                    try {
                        jsonObject = response.getJSONObject(i);
                        Articles articles = new Articles();
                        String id_ley = articles.setIdley(jsonObject.getString("idley"));
                        if (id_ley.equals(idley)){
                            articles.setId(jsonObject.getString("_id"));
                            articles.setName(jsonObject.getString("name"));
                            articles.setDetalle(jsonObject.getString("detalle"));
                            articles.setResumen(jsonObject.getString("resumen"));
                            articles.setExpArticulo(jsonObject.getString("exp_articulo"));
                            listArticle.add(articles);
                        }

                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
                setuprecyclerview(listArticle);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }

    private void setuprecyclerview (List<Articles> lstArticles){
        ReciclerViewArticles myadapter;
        myadapter = new ReciclerViewArticles(this, lstArticles);
        recyclerView = findViewById(R.id.recyclerviewid);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapter);
    }
}
