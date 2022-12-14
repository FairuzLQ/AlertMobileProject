package com.example.alertmobileproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    DBHelper db;
    public CardView card1, card2, card3, card4;
    public ImageButton btnSOS;
    public TextView dateTime;
    public String id_ref11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        card1 = (CardView) findViewById(R.id.cardView);
        card2 = (CardView) findViewById(R.id.cardView2);
        card3 = (CardView) findViewById(R.id.cardView3);
        card4 = (CardView) findViewById(R.id.cardView4);
        btnSOS = findViewById(R.id.btn_sos);
        dateTime = (TextView) findViewById(R.id.textView3);
        btnSOS.setOnClickListener(this);
        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);

        getCurrentDate();

        Intent getRef1 = getIntent();
        id_ref11 = getRef1.getStringExtra("REFERENSI");


        //bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.dashboard);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.dashboard:
                        return true;
                    case R.id.eksplor:
                        startActivity(new Intent(getApplicationContext(),Eksplor.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profil:
                        startActivity(new Intent(getApplicationContext(),Profil.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

    public void onClick(View v){
        Intent i;

        switch (v.getId()){
            case R.id.cardView4:
                i = new Intent(MainActivity.this, PertolonganPertama.class);
                startActivity(i);
                break;
            case R.id.cardView2:
                i = new Intent(MainActivity.this, NomorDarurat.class);
                startActivity(i);
                break;
            case R.id.cardView3:
                i = new Intent(MainActivity.this, RiwayatPenyakit.class);
                startActivity(i);
                break;

            case R.id.cardView:
                i = new Intent(MainActivity.this, KontakDarurat.class);
                startActivity(i);
                break;

            case R.id.btn_sos:
                i = new Intent(MainActivity.this, SOS.class);
                startActivity(i);
                break;
        }
    }

    public void getCurrentDate(){
        Date date = Calendar.getInstance().getTime();

        dateTime.setText(date.toString().substring(0,9)+date.toString().substring(28));

    }
}