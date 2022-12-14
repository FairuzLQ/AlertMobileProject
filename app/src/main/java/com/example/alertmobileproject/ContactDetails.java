package com.example.alertmobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactDetails extends AppCompatActivity {
    private TextView nama, telepon;
    private ImageView profil;
    private String id;

    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        dbHelper = new DBHelper(this);

        //ambil data dari intent kontak adapter
        Intent intent = getIntent();
        id = intent.getStringExtra("contactId");
        //Inisialisasi
        nama = findViewById(R.id.detail_nama);
        telepon = findViewById(R.id.detail_telepon);
        profil = findViewById(R.id.detail_pic);
        loadDatabyId();
    }

    private void loadDatabyId() {
        String selectQuery = "SELECT * FROM "+Constants.TABLE_NAME1 + " WHERE "+Constants.C_ID+ " =\""+id+"\"";

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                        String name = ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_NAMA));
                        String image = ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_IMAGE));
                        String phone = ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_TELEPON));

                        nama.setText(name);
                        telepon.setText(phone);

                        if(image.equals("null")){
                            profil.setImageResource(R.drawable.ic_baseline_person_24);
                        }else{
                            profil.setImageURI(Uri.parse(image));
                        }
            }while(cursor.moveToNext());
        }

        db.close();
    }
}