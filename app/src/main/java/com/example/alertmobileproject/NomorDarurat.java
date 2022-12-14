package com.example.alertmobileproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class NomorDarurat extends AppCompatActivity implements View.OnClickListener  {
    //private CardView btn112, btn113, btn110, btn118, btn115;
    private ImageButton btnBack;
    DBHelper db;
    String rp_ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nomor_darurat);

        db = new DBHelper(this);

        getSupportActionBar().hide();

        btnBack = findViewById(R.id.btn_back2);
        /*btn112 = (CardView) findViewById(R.id.btn_112);
        btn113 = (CardView) findViewById(R.id.btn_113);
        btn110 = (CardView) findViewById(R.id.btn_110);
        btn118 = (CardView) findViewById(R.id.btn_118);
        btn115 = (CardView) findViewById(R.id.btn_115);


        btnBack.setOnClickListener(this);
        btn112.setOnClickListener(this);
        btn113.setOnClickListener(this);
        btn110.setOnClickListener(this);
        btn118.setOnClickListener(this);
        btn115.setOnClickListener(this);*/
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.btn_back2:
                i = new Intent(NomorDarurat.this, MainActivity.class);
                startActivity(i);
                break;
        }
    }

    public void Btn112OnClick(View view) {
        String number = "112";
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+number));

        String timeStampRiwayat = ""+System.currentTimeMillis();
        String[] rp_user_ref = db.ambilRefAkhir();

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

        long rp = db.insertRiwayat(
                ""+number,
                ""+timeStampRiwayat,
                ""+rp_ref
        );
        startActivity(intent);
    }

    public void Btn113OnClick(View view) {
        String number = "113";
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+number));

        String timeStampRiwayat = ""+System.currentTimeMillis();
        String[] rp_user_ref = db.ambilRefAkhir();

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

        long rp = db.insertRiwayat(
                ""+number,
                ""+timeStampRiwayat,
                ""+rp_ref
        );

        startActivity(intent);
    }

    public void Btn110OnClick(View view) {
        String number = "110";
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+number));

        String timeStampRiwayat = ""+System.currentTimeMillis();
        String[] rp_user_ref = db.ambilRefAkhir();

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

        long rp = db.insertRiwayat(
                ""+number,
                ""+timeStampRiwayat,
                ""+rp_ref
        );

        startActivity(intent);
    }

    public void Btn118OnClick(View view) {
        String number = "118";
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+number));

        String timeStampRiwayat = ""+System.currentTimeMillis();
        String[] rp_user_ref = db.ambilRefAkhir();

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

        long rp = db.insertRiwayat(
                ""+number,
                ""+timeStampRiwayat,
                ""+rp_ref
        );

        startActivity(intent);
    }

    public void Btn115OnClick(View view) {
        String number = "115";
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+number));

        String timeStampRiwayat = ""+System.currentTimeMillis();
        String[] rp_user_ref = db.ambilRefAkhir();

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

        long rp = db.insertRiwayat(
                ""+number,
                ""+timeStampRiwayat,
                ""+rp_ref
        );

        startActivity(intent);
    }
}