package com.example.alertmobileproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class RiwayatPanggilan extends AppCompatActivity implements View.OnClickListener {
    private ImageButton btnBack;
    public TextView no1,no2,no3,no4,no5;
    public TextView time1,time2,time3,time4,time5;
    public String riwayat_fix;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_panggilan);

        db = new DBHelper(this);

        getSupportActionBar().hide();
        btnBack = findViewById(R.id.btn_back4);
        no1 = findViewById(R.id.rp_1);
        no2 = findViewById(R.id.rp_2);
        no3 = findViewById(R.id.rp_3);
        no4 = findViewById(R.id.rp_4);
        no5 = findViewById(R.id.rp_5);

        time1 = findViewById(R.id.rpn_1);
        time2 = findViewById(R.id.rpn_2);
        time3 = findViewById(R.id.rpn_3);
        time4 = findViewById(R.id.rpn_4);
        time5 = findViewById(R.id.rpn_5);
        btnBack.setOnClickListener(this);

        loadRiwayat();
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.btn_back4:
                i = new Intent(RiwayatPanggilan.this, Profil.class);
                startActivity(i);
                break;
        }
    }

    public void loadRiwayat(){
        String[] refer_akhir_3 = db.ambilRefAkhir();
        StringBuffer sb_ref_3 = new StringBuffer();
        for(int i=0;i<refer_akhir_3.length;i++){
            sb_ref_3.append(refer_akhir_3[i]);
        }
        String user_refer_3 = sb_ref_3.toString();
        riwayat_fix = user_refer_3;

        String[] showUserRiwayat = db.getUserRiwayat(riwayat_fix);

        Log.d("MARKICOB", riwayat_fix);

        //for(int z=0;z<showUserRiwayat.length;z++){
            //no1.setText(showUserRiwayat[0]);
            //time1.setText(showUserRiwayat[1]);

       // }
        no1.setText(showUserRiwayat[0]);
        time1.setText(showUserRiwayat[1]);
        no2.setText(showUserRiwayat[2]);
        time2.setText(showUserRiwayat[3]);
        no3.setText(showUserRiwayat[4]);
        time3.setText(showUserRiwayat[5]);
        no4.setText(showUserRiwayat[6]);
        time4.setText(showUserRiwayat[7]);
        no5.setText(showUserRiwayat[8]);
        time5.setText(showUserRiwayat[9]);
        //Loop String array nya, pake method length bisa
        /*nama_user.setText(showUserData[1]);
        email_user.setText(showUserData[2]);
        nomor_user.setText(showUserData[3]);
        pesan_user.setText(showUserData[4]);*/
    }
}