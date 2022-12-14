package com.example.alertmobileproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Profil extends AppCompatActivity implements View.OnClickListener {
    public CardView profile1, profile2, profile3, tentang1, tentang2, tentang3;
    public ImageButton btnSOS;
    private TextView namadiProfil, nomordiProfil;
    public Button btnSingout;

    private String profil_user_ref;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        db = new DBHelper(this);
        getSupportActionBar().hide();
        profile1 = (CardView) findViewById(R.id.profil_saya);
        profile2 = (CardView) findViewById(R.id.riwayat_panggilan);
        profile3 = (CardView) findViewById(R.id.riwayat_penyakit);
        tentang1 = (CardView) findViewById(R.id.syarat_ketentuan);
        tentang2 = (CardView) findViewById(R.id.kebijakan_privasi);
        tentang3 = (CardView) findViewById(R.id.panduan_alert);
        btnSOS = findViewById(R.id.btn_sos);
        btnSingout = (Button) findViewById(R.id.signout);

        namadiProfil = (TextView) findViewById(R.id.nama_profil);
        nomordiProfil = (TextView) findViewById(R.id.nomor_profil);




        Boolean checkSession = db.checkSession("ada");
        if(checkSession == false){
            Intent loginIntent = new Intent(Profil.this, activity_login.class);
            startActivity(loginIntent);
            finish();
        }

        btnSingout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean updateSession = db.upgradeSession("kosong",1);
                if(updateSession == true){
                    Toast.makeText(Profil.this, "Sukses untuk Keluar", Toast.LENGTH_SHORT).show();
                    Intent loginIntent = new Intent(Profil.this, activity_login.class);
                    startActivity(loginIntent);
                    finish();
                }
            }
        });

        btnSOS.setOnClickListener(this);
        profile1.setOnClickListener(this);
        profile2.setOnClickListener(this);
        profile3.setOnClickListener(this);
        tentang1.setOnClickListener(this);
        tentang2.setOnClickListener(this);
        tentang3.setOnClickListener(this);

        //bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.profil);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.eksplor:
                        startActivity(new Intent(getApplicationContext(),Eksplor.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profil:
                        return true;
                }
                return false;
            }
        });


        String[] data_profil_user = db.ambilRefAkhir();

        StringBuffer sb_ref_profil = new StringBuffer();
        for(int i=0;i<data_profil_user.length;i++){
            sb_ref_profil.append(data_profil_user[i]);
        }
        String user_refer_profil = sb_ref_profil.toString();
        profil_user_ref = user_refer_profil;
        //Log.d("MARIMARI", profil_user_ref);
        String[] profil_depan_fix = db.getUserData(profil_user_ref);

        namadiProfil.setText(profil_depan_fix[1]);
        nomordiProfil.setText(profil_depan_fix[3]);


    }

    public void onClick(View v){
        Intent i;

        switch (v.getId()){
            case R.id.btn_sos:
                i = new Intent(Profil.this, SOS.class);
                startActivity(i);
                break;

            case R.id.profil_saya:
                i = new Intent(Profil.this, ProfilSayaShow.class);
                startActivity(i);
                break;

            case R.id.riwayat_penyakit:
                i = new Intent(Profil.this, RiwayatPenyakit.class);
                startActivity(i);
                break;

            case R.id.panduan_alert:
                i = new Intent(Profil.this, PanduanAlert.class);
                startActivity(i);
                break;

            case R.id.syarat_ketentuan:
                i = new Intent(Profil.this, SyaratKetentuan.class);
                startActivity(i);
                break;

            case R.id.kebijakan_privasi:
                i = new Intent(Profil.this, PrivacyPolicy.class);
                startActivity(i);
                break;

            case R.id.riwayat_panggilan:
                i = new Intent(Profil.this, RiwayatPanggilan.class);
                startActivity(i);
                break;


        }
    }
}