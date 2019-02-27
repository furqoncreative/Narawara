package com.sera5.narawara;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import java.lang.String;

public class HasilQuiz extends BaseActivity {
    TextView nTotal, nBenar, nSalah, nPoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_quiz);
        nTotal = findViewById(R.id.nilai_jumlah);
        nBenar = findViewById(R.id.nilai_benar);
        nSalah = findViewById(R.id.nilai_salah);
        nPoin = findViewById(R.id.total_poin);


        Intent i=getIntent();

        String soal = i.getStringExtra("total");
        String benar = i.getStringExtra("benar");
        String salah = i.getStringExtra("salah");

        int iBener = Integer.parseInt(benar);
        int iSalah = Integer.parseInt(salah);
        int poin = iBener-iSalah;
        String a = String.valueOf(poin);

        nTotal.setText(soal);
        nBenar.setText(benar);
        nSalah.setText(salah);
        nPoin.setText(a);


    }

}
