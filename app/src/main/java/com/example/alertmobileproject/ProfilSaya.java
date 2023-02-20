package com.example.alertmobileproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ProfilSaya extends AppCompatActivity {
    private Button btnSimpan;
    private ImageButton btnBack;
    private EditText edit_nama, edit_email, edit_nomor, edit_pesan;
    private String id_up, nama_up, email_up, nomor_up, pesan_up, id_refer_up;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_saya);

        db = new DBHelper(this);

        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        btnSimpan = (Button) findViewById(R.id.btn_simpan1);
        btnBack = findViewById(R.id.btn_back3);
        edit_nama = (EditText) findViewById(R.id.editUserNama);
        edit_email = (EditText) findViewById(R.id.editUserEmail);
        edit_nomor = (EditText) findViewById(R.id.editUserNomor);
        edit_pesan = (EditText) findViewById(R.id.editUserPesan);

        Intent getID = getIntent();
        id_up = getID.getStringExtra("ID_USER");

        btnBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(ProfilSaya.this, ProfilSayaShow.class);
                startActivity(back);
                finish();
            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserData1();
            }
        });
    }

    private void updateUserData1(){
        String[] refer_akhir = db.ambilRefAkhir();
        StringBuffer sb_ref = new StringBuffer();
        for(int i=0;i<refer_akhir.length;i++){
            sb_ref.append(refer_akhir[i]);
        }
        String user_refer = sb_ref.toString();

        nama_up = edit_nama.getText().toString();
        email_up = edit_email.getText().toString();
        nomor_up = edit_nomor.getText().toString();
        pesan_up = edit_pesan.getText().toString();
        id_refer_up = user_refer;
        //Log.d("TES", id_up);
        //Log.d("TES111", nama_up);
        db.updateUserData(
                ""+id_up,
                ""+nama_up,
                ""+email_up,
                ""+nomor_up,
                ""+pesan_up,
                ""+id_refer_up
        );
        Toast.makeText(getApplicationContext(), "Data berhasil dirubah!", Toast.LENGTH_SHORT).show();
    }
}