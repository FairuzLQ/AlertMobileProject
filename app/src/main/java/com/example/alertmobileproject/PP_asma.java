package com.example.alertmobileproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;

public class PP_asma extends AppCompatActivity implements View.OnClickListener {
    ExpandableListView expandableListView;
    public CardView back;
    ArrayList<String> listGroup = new ArrayList<>();
    HashMap<String, ArrayList<String>> listChild = new HashMap<>();
    MainAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pp_asma);
        getSupportActionBar().hide();
        back = (CardView) findViewById(R.id.back);
        back.setOnClickListener(this);

        expandableListView = findViewById(R.id.dropdown_pp);

        for(int g=0; g<=2;g++){
            ArrayList<String> arrayList = new ArrayList<>();
            if(g==0){
                listGroup.add("Apa itu Asma?");
                arrayList.add("Asma adalah gangguan pernapasan dimana penderita mengalami kesulitan bernapas yang disebabkan oleh penyempitan saluran pernapasan karna peradangan atau alergi");
            }
            else if(g==1){
                listGroup.add("Bagaimana jika penderita tidak memiliki obat atau inhaler?");
                arrayList.add("Anda harus segera menghubungi fasilitas kesehatan terdekat");
            }
            else{
                listGroup.add("Kapan saya harus menhubungi fasilitas kesehatan terdekat?");
                arrayList.add("Hubungi fasilitas kesehatan jika penderita berhenti bernafas, kelelahan, obat-obatan yang digunakan tidak memberi efek baik, serta saat penderita tidak memiliki obat");
            }

            listChild.put(listGroup.get(g),arrayList);
        }
        adapter = new MainAdapter(listGroup,listChild);
        expandableListView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.back:
                i = new Intent(this, PertolonganPertama.class);
                startActivity(i);
                break;
            case R.id.back_pp:
                i = new Intent(this, PertolonganPertama.class);
                startActivity(i);
                break;

        }
    }
}