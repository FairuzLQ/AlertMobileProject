package com.example.alertmobileproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class PanduanAlert extends AppCompatActivity implements View.OnClickListener{
    private CardView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panduan_alert);
        btnBack = findViewById(R.id.btn_back10);

        getSupportActionBar().hide();

        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.btn_back10:
                i = new Intent(PanduanAlert.this, Profil.class);
                startActivity(i);
                break;
        }
    }
}