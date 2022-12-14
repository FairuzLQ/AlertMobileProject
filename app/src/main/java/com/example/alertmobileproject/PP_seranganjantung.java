package com.example.alertmobileproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import java.util.ArrayList;
import java.util.HashMap;

public class PP_seranganjantung extends AppCompatActivity implements View.OnClickListener {
    ExpandableListView expandableListView;
    public CardView back;
    ArrayList<String> listGroup = new ArrayList<>();
    HashMap<String, ArrayList<String>> listChild = new HashMap<>();
    MainAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pp_seranganjantung);
        getSupportActionBar().hide();
        back = (CardView) findViewById(R.id.btn_back20);
        back.setOnClickListener(this);

        expandableListView = findViewById(R.id.dropdown_pp_seranganjantung);

        for(int g=0; g<=5; g++){
            ArrayList<String> arrayList = new ArrayList<>();
            if(g==0){
                listGroup.add("Apa itu serangan jantung?");
                arrayList.add("Serangan jantung terjadi ketika aliran darah ke otot jantung tiba0-tiba terhambat. Hambatan ini mengakibatkan jantung tidak dapat bekerja secara efektif, sehingga serangan jantung bisa berakibat fatal. Beratnya serangan jantung tergantung pada luas otot jantung yang terdampak");
            }
            else if(g==1){
                listGroup.add("Apa perbedaan antara serangan jantung dan jantung berhenti?");
                arrayList.add("Serangan jantung terjadi ketika aliran darah ke otot jantung terhambat, tapi masih dapat memompa darah dengan tekanan yang lebih rendah sedangkan jantung berhenti adalah kondisi dimana jantung sama sekali berhenti yang menyebabkan pingsan, kehilangan kesadaran dan berhenti bernapas.");
            }
            else if(g==2){
                listGroup.add("Apa ciri-ciri orang mengalami serangan jantung?");
                arrayList.add("Penderita akan mengalami rasa sakit pada bagian dada kiri secara terus menerus dan dapat menyebar ke bagian lengan, leher, rahang, punggung dan perut. Penderita juga akan mengalami keringat dingin dan pada situasi yang lebih lanjut penderita bisa saja tidak bernapas.");
            }
            else if(g==3){
                listGroup.add("Apa yang harus saya lakukan jika orang tersebut memiliki obat untuk digunakan?");
                arrayList.add("Jika penderita memiliki obat jantung, biarkan penderita menggunakannya. Anda bisa saja membantunya.");
            }
            else if(g==4){
                listGroup.add("Bisakah saya memberinya aspirin?");
                arrayList.add("Anda dapat memberikan aspirin kepadanya untuk dikunyah dengan lambat, sehingga mudah masuk ke dalam darahnya. Penderita tidak boleh memakan lebih dari 320 mg untuk satu dosis. Tipe terbaik adalah tablet yang tidak berbungkus.");
            }
            else if(g==5){
                listGroup.add("Apa itu angina?");
                arrayList.add("Angina adalah rasa nyeri di bagian dada karena tidak cukup aliran darah ke jantung yang disebabkan oleh penyempitan pembuluh darah arteri koroner.");
            }

            listChild.put(listGroup.get(g),arrayList);
        }
        adapter = new MainAdapter(listGroup,listChild);
        expandableListView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        if(v.getId()==R.id.btn_back20 || v.getId()==R.id.back_pp_seranganjantung){
            i = new Intent(PP_seranganjantung.this, PertolonganPertama.class);
            startActivity(i);
        }
    }
}