package com.example.alertmobileproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import java.util.ArrayList;
import java.util.HashMap;

public class PP_pendarahan extends AppCompatActivity implements View.OnClickListener {
    ExpandableListView expandableListView;
    public CardView back;
    ArrayList<String> listGroup = new ArrayList<>();
    HashMap<String, ArrayList<String>> listChild = new HashMap<>();
    MainAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pp_pendarahan);
        getSupportActionBar().hide();
        back = (CardView) findViewById(R.id.btn_back15);
        back.setOnClickListener(this);

        expandableListView = findViewById(R.id.dropdown_pp_pendarahan);

        for(int g=0; g<=7;g++){
            ArrayList<String> arrayList = new ArrayList<>();
            if(g==0){
                listGroup.add("Apa yang dapat saya gunakan untuk menekan luka tersebut?");
                arrayList.add("Syok anafilaksis adalah reaksi alergi yang sangat berat yang mengakibatkan seseorang kesulitan untuk bernapas, dapat berlanjut menjadi denyut nadi yang lemah, tekanan darah yang rendah dan menurunnya tingkat kesadaran. Jika seseorang diketahui mengalami syok anafilaksis maka perlu diberikan penanganan lebih lanjut oleh dokter.");
            }
            else if(g==1){
                listGroup.add("Apa yang harus saya lakukan bila pendarahan menembus penutup yang saya gunakan?");
                arrayList.add("Jangan angkat pembalut yang pertama kali pada luka tetapi tambahi penutup (seperti kaos atau handuk) dan tetap tekan dengan kuat. Hubungi fasilitas kesehatan terdekat sesegera mungkin, atau minta orang lain untuk membantu.");
            }
            else if(g==2){
                listGroup.add("Haruskah saya mengikat luka (torniket) terhadap luka yang mengalami pendarahan berat?");
                arrayList.add("Pengikat luka (toriket) TIDAK disarankan untuk orang yang tidak terlatih karena penggunaan yang tidak tepat bisa saja membahayakan dan dapat mengalami kesulitan dalam penggunaan torniket yang efektif. Torniket digunakan untuk mengendalikan pendarahan berat dan hanya disarankan bila menekan langsung sudah tidak efektif.");
            }
            else if(g==3){
                listGroup.add("Orang tersebut pucat, kedinginan dan pusing. Apa artinya?");
                arrayList.add("Artinya tubuh penderita akan mengalami syok karena banyaknya darah yang keluar dari tubuhnya. Jika ini terjadi hubungi fasilitas kesehatan terdekat segera mungkin.");
            }
            else if(g==4){
                listGroup.add("Haruskah saya mengkhawatirkan infeksi atau tertular penyakit dari darah penderita?");
                arrayList.add("Sebaiknya jangan menyentuk darah orang lain secara langsung. Anda dapat menggunakan sarung tangan medis , kantong plastik, atau gunakan tangan penderita untuk menekan lukanya.");
            }
            else if(g==5){
                listGroup.add("Apakah saya harus membersihkan lukanya?");
                arrayList.add("Untuk luka kecil atau bekuan darah, anda dapat membersihkan lukanya untuk menghilangkan kotoran. Jangan membersihkan luka yang mengalami pendarahan berat. Jika anda membersihkan luka yang mengalami pendarahan berat, anda justru akan menghilangkan pembekuan darah dan akhirnya pendarahan akan semakin bertambah banyak.");
            }
            else if(g==6){
                listGroup.add("Apa yang harus saya lakukan jika ada benda yang melekat/menancap pada lukanya?");
                arrayList.add("Jangan dicabut, itu dapat membantu menutupi lubang dan menghentikan aliran darah. Sebaliknya, tekan saja di sekitar benda tersebut. Bila benda yang melekat/menancap dicabut maka dapat memperberat pendarahan.");
            }
            else if(g==7){
                listGroup.add("Bagaimana saya harus menangani mimisan?");
                arrayList.add("Pencet cuping hidung (atau suruh penderita melakukannya) dan posisikan penderita untuk duduk dan kepala di rundukan kebawah lalu minta penderita bernapas melalui mulut. Memencet hidung dapat membantu menghentikan aliran darah. Hubungi fasilitas kesehatan terdekat untuk mendapatkan pertolongan lanjutan bila mimisan tidak berhenti.");
            }

            listChild.put(listGroup.get(g),arrayList);
        }
        adapter = new MainAdapter(listGroup,listChild);
        expandableListView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        if(v.getId()==R.id.btn_back15 || v.getId()==R.id.back_pp_pendarahan){
            i = new Intent(PP_pendarahan.this, PertolonganPertama.class);
            startActivity(i);
        }
    }
}