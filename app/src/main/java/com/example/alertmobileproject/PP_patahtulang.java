package com.example.alertmobileproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import java.util.ArrayList;
import java.util.HashMap;

public class PP_patahtulang extends AppCompatActivity implements View.OnClickListener {
    ExpandableListView expandableListView;
    public CardView back;
    ArrayList<String> listGroup = new ArrayList<>();
    HashMap<String, ArrayList<String>> listChild = new HashMap<>();
    MainAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pp_patahtulang);
        getSupportActionBar().hide();
        back = (CardView) findViewById(R.id.btn_back16);
        back.setOnClickListener(this);

        expandableListView = findViewById(R.id.dropdown_pp_patahtulang);

        for(int g=0; g<=5;g++){
            ArrayList<String> arrayList = new ArrayList<>();
            if(g==0){
                listGroup.add("Bagaimana saya tahu seseorang mengalami patah tulang?");
                arrayList.add("Orang tersebut bisa mengalami memar, nyeri dan pembengkakan atau terbaring dengan posisi yang tidak simetris. Dalam kasus yang berat, anggota tubuh bisa saja patah atau dengan luka terbuka.");
            }
            else if(g==1){
                listGroup.add("Bagaimana saya menopang tulang yang patah?");
                arrayList.add("Jangan menggerakkan jika tidak perlu. Jika anda bisa, letakkan bantalan yang lembut (baju, selimut, dll) pada bagian yang patah untuk menopang dan lakukan pembidaian pada persangkaan tulang yang patah, tetapi jangan memaksanya. Jika anda tidak bisa melakukan pembidaian, terus topang hingga bantuan datang.");
            }
            else if(g==2){
                listGroup.add("Apa yang harus saya lakukan jika orang tersebut tidak mengizinkan saya menopang cederanya?");
                arrayList.add("Anjurkan penderita untuk menopang cederanya sendiri dengan memeganginya. Bantalan juga dapat digunakan untuk menopang cedera.");
            }
            else if(g==3){
                listGroup.add("Jika tulang terlihat tidak seperti posisi normal atau pindah tempat (dislokasi), apakah saya harus mengembalikan ke tempatnya?");
                arrayList.add("Tidak. Jika anggota tubuh mengalami dislokasi atau patah, petugas medis di rumah sakit akan menanganinya. Jangan pernah mencoba untuk mengembalikan ke posisi semula karena dapat menyebabkan cedera yang lebih parah.");
            }
            else if(g==4){
                listGroup.add("Bagaimana jika semuanya terlihat normal dan hanya memar?");
                arrayList.add("Coba cari tahu apa yang menyebabkan cedera (misalnya kejatukhan atau tertimpa sesuatu), karena dapat mengetahui apakah ada tulang yang patah. Namun, biasanya sulit untuk membedakan retak tulang atau keseleo tanpa dilakukan foto Rontgen atau Sinar X. Jika anda ragu, hubungi rumah sakit terdekat.");
            }
            else if(g==5) {
                listGroup.add("Orang tersebut dapat menggerakkan anggota tubuhnya atau dapat berdiri. Apakah berarti penderita tidak mengalami patah tulang?");
                arrayList.add("Belum tentu. Foto Rontgen atau Sinar X biasanya diperlukan untuk mendiagnosa patah tulang. Orang tersebut bisa saja mengalami patah tulang, walaupun mereka dapat menggerakkan anggota tubuhnya. Jika cedera itu mengakibatkan rasa sakit, tidak nyaman atau gejala-gejala yang tidak membaik, segeralah minta pertolongan medis.");
            }

            listChild.put(listGroup.get(g),arrayList);
        }
        adapter = new MainAdapter(listGroup,listChild);
        expandableListView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        if(v.getId()==R.id.btn_back16 || v.getId()==R.id.back_pp_patahtulang){
            i = new Intent(PP_patahtulang.this, PertolonganPertama.class);
            startActivity(i);
        }
    }
}