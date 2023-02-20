package com.example.alertmobileproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Arrays;

public class SOS extends AppCompatActivity implements View.OnClickListener {

    private ImageButton btnBack;
    //private Button btnKPD;
    private MediaPlayer mySound;

    private String number, rp_ref;
    private String message;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        ActivityCompat.requestPermissions(SOS.this,new String[]{Manifest.permission.SEND_SMS,Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(SOS.this,new String[]{Manifest.permission.CALL_PHONE}, PackageManager.PERMISSION_GRANTED);
        dbHelper = new DBHelper(this);
        btnBack = findViewById(R.id.btn_back1);
        //btnSOS = findViewById(R.id.btn_sos2);
        //btnKPD = findViewById(R.id.btn_kpd);
        btnBack.setOnClickListener(this);
        //btnSOS.setOnClickListener(this);
        //btnKPD.setOnClickListener(this);


    }

    public void onClick(View v){
        Intent s;

        switch (v.getId()){
            case R.id.btn_back1:
                s = new Intent(SOS.this, MainActivity.class);
                startActivity(s);
                break;

            case R.id.btn_kpd:
                //popup
        }
    }

    public void BtnCall_onClick(View view) {
        number = "+6281290391717";
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+number));

        String timeStampRiwayat = ""+System.currentTimeMillis();
        String[] rp_user_ref = dbHelper.ambilRefAkhir();

        StringBuffer sb_ref_riwayat = new StringBuffer();
        for(int i=0;i<rp_user_ref.length;i++){
            sb_ref_riwayat.append(rp_user_ref[i]);
        }
        String user_refer_profil = sb_ref_riwayat.toString();
        rp_ref = user_refer_profil;

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            //TODO: Consider calling
            return;
        }

        long rp = dbHelper.insertRiwayat(
                ""+number,
                ""+timeStampRiwayat,
                ""+rp_ref
        );
        startActivity(intent);
    }

    public void BtnSOS_OnClick(View view) {
        if(mySound == null){
            mySound = MediaPlayer.create(this, R.raw.warning);

            mySound.setVolume(1.0f,1.0f);
            mySound.setLooping(true);
            mySound.start();
        }
        else{
            mySound.release();
            mySound = null;
        }

    }

    public void SendSMS(View view){
        dbHelper = new DBHelper(this);
        String[] ambil = dbHelper.ambilData();
        String[] latitude =dbHelper.ambilDataMapLat();
        String[] longitude =dbHelper.ambilDataMapLong();
        Eksplor eksplor = new Eksplor();
        String[] pesan_user = dbHelper.ambilRefAkhir();
        StringBuffer sb_ref_pesan = new StringBuffer();
        for(int i=0;i<pesan_user.length;i++){
            sb_ref_pesan.append(pesan_user[i]);
        }
        String user_refer_pesan = sb_ref_pesan.toString();

        String[] pesan_fix = dbHelper.getUserData(user_refer_pesan);
        String[] r_penyakit_fix = dbHelper.getUserRiwayatPenyakit(user_refer_pesan);
        message = pesan_fix[4]+" https://www.google.com/maps/search/?api=1&query="+latitude[latitude.length-1]+","+longitude[longitude.length-1]+" Saya bergolongan darah "+r_penyakit_fix[2]+" Serta mempunyai riwayat penyakit "+r_penyakit_fix[3];

        for(int j = 0; j < ambil.length;j++){
            SmsManager mySmsManager = SmsManager.getDefault();
            mySmsManager.sendTextMessage(ambil[j],null,message,null,null);
        }

        //SmsManager mySmsManager = SmsManager.getDefault();
        //mySmsManager.sendTextMessage(number,null,message,null,null);
        Toast.makeText(this, "Pesan Darurat Terkirim!", Toast.LENGTH_SHORT).show();
    }
}