package com.example.alertmobileproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class activity_login extends AppCompatActivity {

    private Button btnSignup, btnLogin;
    private EditText username, password;
    public String fix_user_ref_sess;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DBHelper(this);

        getSupportActionBar().hide();

        btnSignup = (Button) findViewById(R.id.btn_signup);
        btnLogin = (Button) findViewById(R.id.btn_login);

        username = (EditText) findViewById(R.id.login_username);
        password = (EditText) findViewById(R.id.login_password);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(activity_login.this, activity_register.class);
                startActivity(registerIntent);
                finish();
            }
        });

        //login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUsername = username.getText().toString();
                String strPassword = password.getText().toString();

                Boolean masuk = db.checkLogin(strUsername,strPassword);
                if(masuk == true){
                    Boolean updateSession = db.upgradeSession("ada",1);
                    if(updateSession == true){
                        String[] user_ref_sess = db.transferReference(strUsername, strPassword);
                        StringBuffer sb_sess = new StringBuffer();
                        for(int i=0;i<user_ref_sess.length;i++){
                            sb_sess.append(user_ref_sess[i]);
                        }
                        fix_user_ref_sess = sb_sess.toString();
                        long rid = db.referData(
                            ""+fix_user_ref_sess
                        );
                        String timeStampRiwayatDummy = ""+System.currentTimeMillis();
                        long rp = db.insertRiwayat(
                                "123456789",
                                ""+timeStampRiwayatDummy,
                                ""+fix_user_ref_sess
                        );
                        long rp1 = db.insertRiwayat(
                                "123456789",
                                ""+timeStampRiwayatDummy,
                                ""+fix_user_ref_sess
                        );
                        long rp2 = db.insertRiwayat(
                                "123456789",
                                ""+timeStampRiwayatDummy,
                                ""+fix_user_ref_sess
                        );
                        long rp3 = db.insertRiwayat(
                                "123456789",
                                ""+timeStampRiwayatDummy,
                                ""+fix_user_ref_sess
                        );
                        long rp4 = db.insertRiwayat(
                                "123456789",
                                ""+timeStampRiwayatDummy,
                                ""+fix_user_ref_sess
                        );
                        //Log.d("DISINI", fix_user_ref_sess);
                        Toast.makeText(getApplicationContext(), "Selamat Datang!", Toast.LENGTH_SHORT).show();
                        Intent mainIntent = new Intent(activity_login.this, MainActivity.class);
                        mainIntent.putExtra("REFERENSI", fix_user_ref_sess);


                        startActivity(mainIntent);
                        finish();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Gagal masuk!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}