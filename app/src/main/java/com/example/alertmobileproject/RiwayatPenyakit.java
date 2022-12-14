package com.example.alertmobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class RiwayatPenyakit extends AppCompatActivity {
    private Button btnSimpan;
    private ImageButton btnBack;
    private EditText tglLahir, golDarah, riPenyakit;
    private String userID_ref2;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_penyakit);

        db = new DBHelper(this);
        getSupportActionBar().hide();

        btnBack = findViewById(R.id.btn_back5);
        btnSimpan = findViewById(R.id.btn_simpan2);

        tglLahir = (EditText) findViewById(R.id.edit_tgl_lahir);
        golDarah = (EditText) findViewById(R.id.edit_gol_darah);
        riPenyakit = (EditText) findViewById(R.id.edit_riwayat_penyakit);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i_update = new Intent(RiwayatPenyakit.this, RiwayatPenyakitUpdate.class);
                String[] refer_akhir_3 = db.ambilRefAkhir();
                StringBuffer sb_ref_3 = new StringBuffer();
                for(int j=0;j<refer_akhir_3.length;j++){
                    sb_ref_3.append(refer_akhir_3[j]);
                }
                String user_refer_3 = sb_ref_3.toString();
                userID_ref2 = user_refer_3;
                String[] showUserDataID = db.getUserRiwayatPenyakit(userID_ref2);
                Log.d("UJICOBA", showUserDataID[0]);
                i_update.putExtra("ID_USER_PENYAKIT", showUserDataID[0]);
                i_update.putExtra("TGL_USER", showUserDataID[1]);
                i_update.putExtra("GOL_USER", showUserDataID[2]);
                i_update.putExtra("R_PENYAKIT_USER", showUserDataID[3]);





                startActivity(i_update);
                finish();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(RiwayatPenyakit.this, Profil.class);
                startActivity(back);
            }
        });

        loadUserPenyakit();
    }

    public void loadUserPenyakit(){
        String[] refer_akhir_2 = db.ambilRefAkhir();
        StringBuffer sb_ref_2 = new StringBuffer();
        for(int i=0;i<refer_akhir_2.length;i++){
            sb_ref_2.append(refer_akhir_2[i]);
        }
        String user_refer_2 = sb_ref_2.toString();
        userID_ref2 = user_refer_2;

        String[] showUserData = db.getUserRiwayatPenyakit(userID_ref2);
        //Loop String array nya, pake method length bisa
        tglLahir.setText(showUserData[1]);
        golDarah.setText(showUserData[2]);
        riPenyakit.setText(showUserData[3]);
    }
}