package com.example.amiapp;

import android.os.Bundle;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeLaw extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    private final String JSON_URL = "http://10.0.2.2:3000/api/ley";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private  List<Law> listLaw;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_law);


        listLaw = new ArrayList<>();
        cargarLeyes();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_settings, R.id.nav_close)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_law, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void cargarLeyes(){

        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;
                for (int i =0; i < response.length(); i++){
                    try {
                        jsonObject = response.getJSONObject(i);
                        Law law = new Law();
                        String est = law.setEstado(jsonObject.getString("estado"));
                        if (est.equals("Aprobado")){
                            law.setId(jsonObject.getString("_id"));
                            law.setNombre(jsonObject.getString("nombre"));
                            law.setDescripcion(jsonObject.getString("descripcion"));
                            law.setFechaPublicacion(jsonObject.getString("fecha_publicacion"));
                            law.setCodDecreto(jsonObject.getString("cod_decreto"));
                            law.setProponente(jsonObject.getString("proponente"));
                            listLaw.add(law);
                        }

                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
                setuprecyclerview(listLaw);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }

    private void setuprecyclerview (List<Law> lstLaw){
        RecyclerViewAdapter myadapter;
        myadapter = new RecyclerViewAdapter(this, lstLaw);
        recyclerView = findViewById(R.id.recyclerviewid);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapter);
    }
}
