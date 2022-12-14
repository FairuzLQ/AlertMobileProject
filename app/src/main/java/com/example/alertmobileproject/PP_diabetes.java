package com.example.alertmobileproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import java.util.ArrayList;
import java.util.HashMap;

public class PP_diabetes extends AppCompatActivity implements View.OnClickListener {
    ExpandableListView expandableListView;
    public CardView back;
    ArrayList<String> listGroup = new ArrayList<>();
    HashMap<String, ArrayList<String>> listChild = new HashMap<>();
    MainAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pp_diabetes);
        getSupportActionBar().hide();
        back = (CardView) findViewById(R.id.btn_back18);
        back.setOnClickListener(this);

        expandableListView = findViewById(R.id.dropdown_pp_tersedak);

        for(int g=0; g<=2; g++){
            ArrayList<String> arrayList = new ArrayList<>();
            if(g==0){
                listGroup.add("Bagaimana saya melakukan tekanan di bagian perut?");
                arrayList.add("Berdiri di belakang orang yang tersedak, rangkul mereka dan taruh kepalan kedua tangan anda di sekitar perutnya. Tarik tangan anda ke perut dan ke atas. Ulangi hingga 5 kali. Tindakan ini disebut gerakan Heimlick. Jangan melakukan tindakan ini pada anak di bawah satu tahun.");
            }
            else if(g==1){
                listGroup.add("Apakah saya dapat memperlakukan anak yang tersedak sama seperti pada orang dewasa?");
                arrayList.add("YA. Dengan tekanan di bagian perut dapat efektif untuk membersihkan sumbatan pernapasan pada anak diatas usia satu tahun. Jangan menggantung tubuh anak dengan terbalik untuk mengeluarkan benda yang tertelan. Tindakan ini tidak efektif dan sebaliknya dapat memperberat cedera apalagi jika anda menjatuhkannya.");
            }
            else if(g==2){
                listGroup.add("Bagaimana saya memperlakukan bayi yang tersedak?");
                arrayList.add("Untuk bayi (anak di bawah satu tahun), letakkan kepala mereka lebih rendah dari dada, kemudian topang kepala dan lehernya. Lalu tepuk punggung atasnya 5 kali dan tekan 5 kali dibagian dada, tepat di tengah di bawah garis puting. Ulangi tindakan ini hingga benda yang tertekan itu keluar atau hingga bayi menangis.");
            }

            listChild.put(listGroup.get(g),arrayList);
        }
        adapter = new MainAdapter(listGroup,listChild);
        expandableListView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        if(v.getId()==R.id.btn_back18 || v.getId()==R.id.back_pp_tersedak){
            i = new Intent(PP_diabetes.this, PertolonganPertama.class);
            startActivity(i);
        }
    }
}