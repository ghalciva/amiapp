package com.example.amiapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class LawDetail extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    Button btnCompartir, btnAgree, btnDesagree;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law_detail2);

        btnCompartir = findViewById(R.id.btnCompartir);
        btnAgree = findViewById(R.id.btnAgree);
        btnDesagree = findViewById(R.id.btnDesagree);


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

    }
}






