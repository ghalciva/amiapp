package com.example.amiapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SetPass extends AppCompatActivity {

    Button btnSaveNewPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setpass);

        btnSaveNewPass = findViewById(R.id.btnCreateAcc);
        btnSaveNewPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //guardar contraseña
            }
        });

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
