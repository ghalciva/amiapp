package com.example.amiapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.amiapp.ui.home.HomeFragment;
import com.example.amiapp.ui.slideshow.SlideshowFragment;
import com.example.amiapp.ui.tools.ToolsFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.Inflater;

public class HomeLaw extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

        private final String JSON_URL = "http://10.0.2.2:3000/api/ley";
    //private final String JSON_URL = "http://68.66.207.7:3000/api/ley";
        private final String url = "http://10.0.2.2:3000/api/ciudadano";
    //private static String url = "http://68.66.207.7:3000/api/ciudadano";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private  List<Law> listLaw;
    ArrayList<HashMap<String, String>> listCitizen;
    ListView lv;
    RecyclerView recyclerView;
    Button buttonSalir;
    Button buttonver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_law);

        listLaw = new ArrayList<>();
        listCitizen = new ArrayList<>();
        buttonver = findViewById(R.id.button2);
        buttonSalir = findViewById(R.id.button);

        View v = LayoutInflater.from(this).inflate(R.layout.fragment_tools, null);
        lv = v.findViewById(R.id.list);

        cargarLeyes();
        cargarData();

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

    public void salir(View v){
        if (v.isPressed()){
            Intent intent = new Intent(HomeLaw.this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void ver(View v){
        if (v.isPressed()){
            cargarData();
            v.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.nav_home) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
                        String est = law.getEstado(jsonObject.getString("estado"));
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

    public void cargarData(){
        new GetEvents().execute();
    }


    private class GetEvents extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... arg0) {

            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.i("tag", "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    // Getting JSON Array node
                    JSONArray events = new JSONArray(jsonStr);

                    // looping through All Events
                    for (int i = 0; i < events.length(); i++) {
                        JSONObject c = events.getJSONObject(i);

                        String id = c.getString("_id");
                        String name = c.getString("nombre");
                        String date = c.getString("apellido");
                        String address = c.getString("cedula");
                        String description = c.getString("contrasena");

                        // tmp hash map for single event
                        HashMap<String, String> event = new HashMap<>();

                        // adding each child node to HashMap key => value
                        event.put("_id", id);
                        event.put("nombre", name);
                        event.put("apellido", date);
                        event.put("cedula", address);
                        event.put("contrasena",description);

                        // adding contact to contact list
                        listCitizen.add(event);
                    }
                } catch (final JSONException e) {
                    Log.e("tag", "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e("tag", "Couldn't get json from server. No internet connection!");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. No internet connection!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            ListAdapter adapter;
            adapter = new SimpleAdapter(
                    getApplicationContext(), listCitizen,
                    R.layout.list_user, new String[]{"nombre", "apellido", "cedula",
                    "contrasena"}, new int[]{R.id.nombre,
                    R.id.apellido, R.id.cedula, R.id.contrasena});
            lv.setAdapter(adapter);
        }
    }


}
