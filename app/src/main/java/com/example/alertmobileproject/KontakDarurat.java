package com.example.alertmobileproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class KontakDarurat extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton fab;
    private RecyclerView output_kontak;
    public CardView btnBack;
    private DBHelper dbHelper;

    private KontakAdapter kontakAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontak_darurat);

        dbHelper = new DBHelper(this);

        getSupportActionBar().hide();

        fab = findViewById(R.id.fab);
        btnBack = findViewById(R.id.btn_back11);
        output_kontak = findViewById(R.id.output_kontak);

        output_kontak.setHasFixedSize(true);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KontakDarurat.this, InputKontak.class);
                intent.putExtra("isEditMode",false);
                startActivity(intent);

            }
        });

        btnBack.setOnClickListener(this);
        loadData();
    }

    private void loadData() {
        kontakAdapter = new KontakAdapter(this,dbHelper.getAllData());
        output_kontak.setAdapter(kontakAdapter);
    }

    @Override
    protected void onResume(){
        super.onResume();
        loadData();
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.btn_back11:
                i = new Intent(KontakDarurat.this, Profil.class);
                startActivity(i);
                break;
        }
    }
}