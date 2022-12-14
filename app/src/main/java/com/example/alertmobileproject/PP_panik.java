package com.example.alertmobileproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import java.util.ArrayList;
import java.util.HashMap;

public class PP_panik extends AppCompatActivity implements View.OnClickListener {
    ExpandableListView expandableListView;
    public CardView back;
    ArrayList<String> listGroup = new ArrayList<>();
    HashMap<String, ArrayList<String>> listChild = new HashMap<>();
    MainAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pp_panik);
        getSupportActionBar().hide();
        back = (CardView) findViewById(R.id.btn_back19);
        back.setOnClickListener(this);

        expandableListView = findViewById(R.id.dropdown_pp_panik);

        for(int g=0; g<=2; g++){
            ArrayList<String> arrayList = new ArrayList<>();
            if(g==0){
                listGroup.add("Apakah saya akan trauma jika saya menolong orang yang mengalami kecelakaan atau panik/tertekan?");
                arrayList.add("Tidak, umumnya orang tidak akan trauma ketika menolong orang lain dan sebaliknya. Pada umumnya seseorang merasa senang ketika dapat membantu orang lain. Jiak anda merasa takut/tertekan setelah kejadian itu, bicarakan dengan orang yang anda percayai. Jika anda khawatir dengan kesehatan anda, temui seorang psikiater atau dokter");
            }
            else if(g==1){
                listGroup.add("Bagaimana jika penderita tidak tahu atau tidak dapat memberitahu apa yang dia butuhkan?");
                arrayList.add("Tetap dengarkan, dan coba hubungi keluarganya atas nama penderita atau dapat juga menghubungi pihak berwenang (polisi/rumah sakit/ambulan). Tetaplah tenang dan yakinkan penderita bahwa anda dapat menolongnya.");
            }
            else if(g==2){
                listGroup.add("Apa yang harus saya lakukan kepada penderita yang bereaksi berlebihan?");
                arrayList.add("Setiap orang memiliki kebutuhan yang berbeda-beda dan pengalaman hidup yang berbeda pula dan oleh karena itu reaksinya tentu berbeda dengan orang lain. Upayakan untuk menunjukkan rasa empati dan dengarkan apa yang mereka katakan.");
            }

            listChild.put(listGroup.get(g),arrayList);
        }
        adapter = new MainAdapter(listGroup,listChild);
        expandableListView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        if(v.getId()==R.id.btn_back19 || v.getId()==R.id.back_pp_panik){
            i = new Intent(PP_panik.this, PertolonganPertama.class);
            startActivity(i);
        }
    }
}