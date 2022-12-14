package com.example.alertmobileproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import java.util.ArrayList;
import java.util.HashMap;

public class PP_lukabakar extends AppCompatActivity implements View.OnClickListener {
    ExpandableListView expandableListView;
    public CardView back;
    ArrayList<String> listGroup = new ArrayList<>();
    HashMap<String, ArrayList<String>> listChild = new HashMap<>();
    MainAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pp_lukabakar);
        getSupportActionBar().hide();
        back = (CardView) findViewById(R.id.btn_back17);
        back.setOnClickListener(this);

        expandableListView = findViewById(R.id.dropdown_pp_lukabakar);

        for(int g=0; g<=4;g++){
            ArrayList<String> arrayList = new ArrayList<>();
            if(g==0){
                listGroup.add("Bisakah saya menaruh mentega atau bahan lainnya (krim, kecap, pasta gigi, oli, sabun, dll) untuk membantu penyembuhan pada luka bakar?");
                arrayList.add("TIDAK. Mentega tidak akan mendinginkan luka bakar. Semua jenis minyak akan mempertahankan panas, hal ini berlawanan dengan yang anda inginkan dan akan menyebabkan kerusakan kulit yang lebih berat.");
            }
            else if(g==1){
                listGroup.add("Bisakah saya menggunakan es batu untuk mendinginkan luka bakar?");
                arrayList.add("TIDAK. Es batu dapat merusak kulit. Gunakan air yang mengalir. Jika anda tidak memiliki air yang mengalir, siram dengan cairan dingin lainnya.");
            }
            else if(g==2){
                listGroup.add("Kapan saya harus pergi ke rumah sakit?");
                arrayList.add("Anda harus segera datang ke rumah sakit jika yang mengalami luka bakar adalah anak-anak, luka bakar yang melepuh, luka bakar yang terjadi pada lebih dari satu bagian tubuh.");
            }
            else if(g==3){
                listGroup.add("Bisakah saya mengunakan perban untuk menutupi luka bakar?");
                arrayList.add("TIDAK. Jangan gunakan perban atau sejenisnya karena akan lengket pada kulit dan dapat mengakibatkan kerusakan kulit yang lebih parah. Tutupi bagian yang terbakar dengan plastik bersih seperti penutup makanan atau kantong plastik biasa.");
            }
            else if(g==4){
                listGroup.add("Jika baju lengket pada bagian yang terbakar, apakah saya harus melepaskannya?");
                arrayList.add("TIDAK. Jangan coba untuk melepaskan apapun yang lengket pada luka yang terbakar karena dapat mengakibatkan kerusakan yang lebih berat. Tapi lepaskan pakaian atau perhiasan yang ada di dekat bagian yang terbakar.");
            }

            listChild.put(listGroup.get(g),arrayList);
        }
        adapter = new MainAdapter(listGroup,listChild);
        expandableListView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        if(v.getId()==R.id.btn_back17 || v.getId()==R.id.back_pp_lukabakar){
            i = new Intent(PP_lukabakar.this, PertolonganPertama.class);
            startActivity(i);
        }
    }
}