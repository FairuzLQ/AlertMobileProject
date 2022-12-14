package com.example.alertmobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class RiwayatPenyakitUpdate extends AppCompatActivity {
    private Button btnSimpan;
    private ImageButton btnBack;
    private EditText edit_tglLahir, edit_golDarah, edit_rPenyakit;
    private String id_pen, tgl_pen, gol_pen, penyakit_pen, id_refer_pen;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_penyakit_update);

        db = new DBHelper(this);
        getSupportActionBar().hide();
        btnBack = findViewById(R.id.btn_back5);
        btnSimpan = findViewById(R.id.btn_updatePenyakit);
        edit_tglLahir = (EditText) findViewById(R.id.edit_tgl_lahir_update);
        edit_golDarah = (EditText) findViewById(R.id.edit_gol_darah_update);
        edit_rPenyakit = (EditText) findViewById(R.id.edit_riwayat_penyakit_update);

        Intent getIDuser = getIntent();
        id_pen = getIDuser.getStringExtra("ID_USER_PENYAKIT");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backOy = new Intent(RiwayatPenyakitUpdate.this, RiwayatPenyakit.class);
                startActivity(backOy);
                finish();
            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserData2();
            }
        });
    }

    private void updateUserData2(){
        String[] refer_akhir = db.ambilRefAkhir();
        StringBuffer sb_ref = new StringBuffer();
        for(int i=0;i<refer_akhir.length;i++){
            sb_ref.append(refer_akhir[i]);
        }
        String user_refer = sb_ref.toString();

        tgl_pen = edit_tglLahir.getText().toString();
        gol_pen = edit_golDarah.getText().toString();
        penyakit_pen = edit_rPenyakit.getText().toString();

        id_refer_pen = user_refer;
        //Log.d("TES", id_up);
        //Log.d("TES111", nama_up);
        db.updateUserPenyakit(
                ""+id_pen,
                ""+tgl_pen,
                ""+gol_pen,
                ""+penyakit_pen,
                ""+id_refer_pen
        );
        Toast.makeText(getApplicationContext(), "Data berhasil dirubah!", Toast.LENGTH_SHORT).show();
    }
}