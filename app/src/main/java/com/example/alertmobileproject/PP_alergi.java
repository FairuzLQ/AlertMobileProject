package com.example.alertmobileproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import java.util.ArrayList;
import java.util.HashMap;

public class PP_alergi extends AppCompatActivity implements View.OnClickListener {
    ExpandableListView expandableListView;
    public CardView back;
    ArrayList<String> listGroup = new ArrayList<>();
    HashMap<String, ArrayList<String>> listChild = new HashMap<>();
    MainAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pp_alergi);

        getSupportActionBar().hide();

        back = (CardView) findViewById(R.id.btn_back13);
        back.setOnClickListener(this);

        expandableListView = findViewById(R.id.dropdown_pp_alergi);

        for(int g=0; g<=1;g++){
            ArrayList<String> arrayList = new ArrayList<>();
            if(g==0){
                listGroup.add("Apa yang dimaksud syok anafilaksis?");
                arrayList.add("Syok anafilaksis adalah reaksi alergi yang sangat berat yang mengakibatkan seseorang kesulitan untuk bernapas, dapat berlanjut menjadi denyut nadi yang lemah, tekanan darah yang rendah dan menurunnya tingkat kesadaran. Jika seseorang diketahui mengalami syok anafilaksis maka perlu diberikan penanganan lebih lanjut oleh dokter.");
            }
            else if(g==1){
                listGroup.add("Makanan apa saja yang dapat menyebabkan reaksi alergi?");
                arrayList.add("Umumnya reaksi alergi disebabkan oleh serbuk sari, debu, sengatan binatang dan beberapa bahan tertentu (misal: bedak, karet lateks), serta beberapa jenis makanan, seperti kacang-kacangan dan produk susu.");
            }

            listChild.put(listGroup.get(g),arrayList);
        }
        adapter = new MainAdapter(listGroup,listChild);
        expandableListView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        if(v.getId()==R.id.btn_back13 || v.getId()==R.id.back_pp_alergi){
            i = new Intent(PP_alergi.this, PertolonganPertama.class);
            startActivity(i);
        }
    }
}