package com.example.alertmobileproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SyaratKetentuan extends AppCompatActivity implements View.OnClickListener{
    private CardView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syarat_ketentuan);
        btnBack = findViewById(R.id.btn_back8);
        getSupportActionBar().hide();
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.btn_back8:
                i = new Intent(SyaratKetentuan.this, Profil.class);
                startActivity(i);
                break;
        }
    }
}