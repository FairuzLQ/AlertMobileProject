package com.example.alertmobileproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class ProfilSayaShow extends AppCompatActivity {
    private Button btnSimpanShow;
    private ImageButton btnBackShow;
    private String userID_ref;
    private EditText nama_user, email_user, nomor_user, pesan_user;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_saya_show);
        db = new DBHelper(this);
        getSupportActionBar().hide();

        btnSimpanShow = findViewById(R.id.btn_simpan2);
        btnBackShow = findViewById(R.id.btn_back20);

        //Find by ID
        nama_user = (EditText) findViewById(R.id.showNama);
        email_user = (EditText) findViewById(R.id.showEmail);
        nomor_user = (EditText) findViewById(R.id.showNomor);
        pesan_user = (EditText) findViewById(R.id.showPesan);



        btnSimpanShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfilSayaShow.this, ProfilSaya.class);
                String[] refer_akhir_3 = db.ambilRefAkhir();
                StringBuffer sb_ref_3 = new StringBuffer();
                for(int j=0;j<refer_akhir_3.length;j++){
                    sb_ref_3.append(refer_akhir_3[j]);
                }
                String user_refer_3 = sb_ref_3.toString();
                userID_ref = user_refer_3;
                String[] showUserDataID = db.getUserData(userID_ref);
                i.putExtra("ID_USER", showUserDataID[0]);
                i.putExtra("NAMA_USER", showUserDataID[1]);
                i.putExtra("EMAIL_USER", showUserDataID[2]);
                i.putExtra("NOMOR_USER", showUserDataID[3]);
                i.putExtra("PESAN_USER", showUserDataID[4]);
                startActivity(i);
                finish();
            }
        });

        btnBackShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(ProfilSayaShow.this, Profil.class);
                startActivity(i2);
                finish();
            }
        });

        loadUserData();
    }

    private void loadUserData(){
        String[] refer_akhir_2 = db.ambilRefAkhir();
        StringBuffer sb_ref_2 = new StringBuffer();
        for(int i=0;i<refer_akhir_2.length;i++){
            sb_ref_2.append(refer_akhir_2[i]);
        }
        String user_refer_2 = sb_ref_2.toString();
        userID_ref = user_refer_2;

        String[] showUserData = db.getUserData(userID_ref);
        //Loop String array nya, pake method length bisa
        nama_user.setText(showUserData[1]);
        email_user.setText(showUserData[2]);
        nomor_user.setText(showUserData[3]);
        pesan_user.setText(showUserData[4]);

    }


}