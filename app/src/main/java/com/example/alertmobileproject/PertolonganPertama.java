package com.example.alertmobileproject;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import android.content.Intent;

public class PertolonganPertama extends AppCompatActivity implements View.OnClickListener  {

    //Variabel list untuk penyakit
    ListView listView;
    public CardView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pertolongan_pertama);

        getSupportActionBar().hide();

        //Array untuk menyimpan penyakit2
        String[] arrPenyakit = {
                "Alergi/Anafilaksis","Serangan Asma", "Pendarahan", "Patah Tulang"
                ,"Luka Bakar","Tersedak","Panik/Histeria","Serangan Jantung"
        };
        //Adapter array
        ArrayAdapter adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                arrPenyakit
        );
        listView = (ListView)findViewById(R.id.listview_penyakit);
        listView.setAdapter(adapter);

        back = (CardView) findViewById(R.id.back);
        back.setOnClickListener(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0){
                    startActivity(new Intent(PertolonganPertama.this, PP_alergi.class));

                }
                else if(i == 1){
                    startActivity(new Intent(PertolonganPertama.this, PP_asma.class));
                }
                else if(i == 2){
                    startActivity(new Intent(PertolonganPertama.this, PP_pendarahan.class));
                }
                else if(i == 3){
                    startActivity(new Intent(PertolonganPertama.this, PP_patahtulang.class));
                }
                else if(i == 4){
                    startActivity(new Intent(PertolonganPertama.this, PP_lukabakar.class));
                }
                else if(i == 5){
                    startActivity(new Intent(PertolonganPertama.this, PP_diabetes.class));
                }
                else if(i == 6){
                    startActivity(new Intent(PertolonganPertama.this, PP_panik.class));
                }
                else if(i == 7){
                    startActivity(new Intent(PertolonganPertama.this, PP_seranganjantung.class));
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.back:
                i = new Intent(this, MainActivity.class);
                startActivity(i);
                break;
        }
    }
}