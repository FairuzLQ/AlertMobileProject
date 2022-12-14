package com.example.alertmobileproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;

public class activity_register extends AppCompatActivity {

    DBHelper db;
    private Button btnRegister, btnLogin;
    private EditText username, password, repassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DBHelper(this);

        getSupportActionBar().hide();

        btnRegister = (Button) findViewById(R.id.btn_register);
        btnLogin = (Button) findViewById(R.id.btn_login);

        username = (EditText) findViewById(R.id.regis_username);
        password = (EditText) findViewById(R.id.regis_password);
        repassword = (EditText) findViewById(R.id.regis_repassword);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUsername = username.getText().toString();
                String strPassword = password.getText().toString();
                String strRePassword = repassword.getText().toString();

                if(strPassword.equals(strRePassword)){
                    Boolean daftar = db.insertUser(strUsername,strPassword);
                    if(daftar == true){
                        //tambahin dbhelper insert user data
                        String[] user_ref = db.ambilDataIdUser();
                        StringBuffer sb = new StringBuffer();
                        for(int i=0;i<user_ref.length;i++){
                            sb.append(user_ref[i]);
                        }
                        String fix_user_ref = sb.toString();
                        //Log.d("DISISNIIIIIIISASASA", fix_user_ref);
                        long iud = db.userData(
                                "User Baru",
                                "User Baru",
                                "User Baru",
                                "User Baru",
                                ""+fix_user_ref
                        );

                        long rp2 = db.insertR_Penyakit(
                                "01-01-2001",
                                "A",
                                "Diabetes, Patah Tulang",
                                ""+fix_user_ref
                        );
                        Toast.makeText(getApplicationContext(), "Pendaftaran Berhasil!", Toast.LENGTH_SHORT).show();
                        Intent loginIntent = new Intent(activity_register.this, activity_login.class);
                        startActivity(loginIntent);
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(), "Pendaftaran Gagal!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Password tidak sama!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(activity_register.this, activity_login.class);
                startActivity(loginIntent);
                finish();
            }
        });
    }




}