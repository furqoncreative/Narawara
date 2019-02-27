package com.sera5.narawara;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sera5.narawara.Model.SoalQuiz;

import java.util.ArrayList;
import java.util.Locale;

public class QuizActivity extends BaseActivity {

    TextView soal, waktu;
    Button b1, b2, b3, b4;
    int benar = 0;
    int salah = 0;
    int total= 0;
    CountDownTimer ct;

    DatabaseReference databaseReference;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ct.cancel();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ct.cancel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        b1 = findViewById(R.id.pil1);
        b2 = findViewById(R.id.pil2);
        b3 = findViewById(R.id.pil3);
        b4 = findViewById(R.id.pil4);
        soal = findViewById(R.id.txt_soal);
        waktu = findViewById(R.id.waktu);

        dapatSoal();
        reverseTimer(60, waktu);

    }

    public void dapatSoal() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Question");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<SoalQuiz> sq = new ArrayList<>();
                for(DataSnapshot ds: dataSnapshot.getChildren()) {
                    String soal_ = ds.child("soal").getValue(String.class);
                    String jawaban = ds.child("jawaban").getValue(String.class);
                    String pil1 = ds.child("pil1").getValue(String.class);
                    String pil2 = ds.child("pil2").getValue(String.class);
                    String pil3 = ds.child("pil3").getValue(String.class);
                    String pil4 = ds.child("pil4").getValue(String.class);

                    SoalQuiz soalQuiz = new SoalQuiz(soal_,jawaban,pil1,pil2,pil3,pil4);

                    sq.add(soalQuiz);
                }

                tampilsoal(sq.size()-1, sq);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void tampilsoal(int ts, ArrayList<SoalQuiz> sq) {
        if(ts<0) {
            Toast.makeText(getApplicationContext(), "Game Over",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(QuizActivity.this, HasilQuiz.class);
            i.putExtra("total",String.valueOf(total));
            i.putExtra("benar",String.valueOf(benar));
            i.putExtra("salah",String.valueOf(salah));
            startActivity(i);
            finish();
        } else {

            SoalQuiz sk = sq.get(ts);
            soal.setText(sk.getSoal());
            b1.setText(sk.getPil1());
            b2.setText(sk.getPil2());
            b3.setText(sk.getPil3());
            b4.setText(sk.getPil4());

            adjustbutton2();

            b1.setOnClickListener(v -> {
                adjustbutton();
                if (b1.getText().toString().equals(sk.getJawaban())) {
                    Toast.makeText(getApplicationContext(), "Jawaban Benar", Toast.LENGTH_SHORT).show();
                    b1.setBackgroundColor(Color.GREEN);
                    Handler handler = new Handler();

                    handler.postDelayed(() -> {
                        benar++;
                        b1.setBackgroundColor(Color.parseColor("#b71c1c"));
                        tampilsoal(ts - 1, sq);
                    }, 1500);
                } else {
                    salah++;
                    Toast.makeText(getApplicationContext(), "Jawaban Salah", Toast.LENGTH_SHORT).show();
                    b1.setBackgroundColor(Color.YELLOW);

                    if (b2.getText().toString().equals(sk.getJawaban())) {
                        b2.setBackgroundColor(Color.GREEN);
                    } else if (b3.getText().toString().equals(sk.getJawaban())) {
                        b3.setBackgroundColor(Color.GREEN);
                    } else if (b4.getText().toString().equals(sk.getJawaban())) {
                        b4.setBackgroundColor(Color.GREEN);
                    }

                    Handler handler = new Handler();
                    handler.postDelayed(() -> {
                        b1.setBackgroundColor(Color.parseColor("#b71c1c"));
                        b2.setBackgroundColor(Color.parseColor("#b71c1c"));
                        b3.setBackgroundColor(Color.parseColor("#b71c1c"));
                        b4.setBackgroundColor(Color.parseColor("#b71c1c"));
                        tampilsoal(ts - 1, sq);
                    }, 1500);
                } total++;
            });

            b2.setOnClickListener(v -> {
                adjustbutton();
                if (b2.getText().toString().equals(sk.getJawaban())) {
                    Toast.makeText(getApplicationContext(), "Jawaban Benar", Toast.LENGTH_SHORT).show();
                    b2.setBackgroundColor(Color.GREEN);
                    Handler handler = new Handler();

                    handler.postDelayed(() -> {
                        benar++;
                        b2.setBackgroundColor(Color.parseColor("#b71c1c"));
                        tampilsoal(ts - 1, sq);
                    }, 1500);
                } else {
                    salah++;
                    Toast.makeText(getApplicationContext(), "Jawaban Salah", Toast.LENGTH_SHORT).show();
                    b2.setBackgroundColor(Color.YELLOW);

                    if (b1.getText().toString().equals(sk.getJawaban())) {
                        b1.setBackgroundColor(Color.GREEN);
                    } else if (b3.getText().toString().equals(sk.getJawaban())) {
                        b3.setBackgroundColor(Color.GREEN);
                    } else if (b4.getText().toString().equals(sk.getJawaban())) {
                        b4.setBackgroundColor(Color.GREEN);
                    }

                    Handler handler = new Handler();
                    handler.postDelayed(() -> {
                        b1.setBackgroundColor(Color.parseColor("#b71c1c"));
                        b2.setBackgroundColor(Color.parseColor("#b71c1c"));
                        b3.setBackgroundColor(Color.parseColor("#b71c1c"));
                        b4.setBackgroundColor(Color.parseColor("#b71c1c"));
                        tampilsoal(ts - 1, sq);
                    }, 1500);
                }total++;
            });

            b3.setOnClickListener(v -> {
                adjustbutton();
                if (b3.getText().toString().equals(sk.getJawaban())) {
                    Toast.makeText(getApplicationContext(), "Jawaban Benar", Toast.LENGTH_SHORT).show();
                    b3.setBackgroundColor(Color.GREEN);
                    Handler handler = new Handler();

                    handler.postDelayed(() -> {
                        benar++;
                        b3.setBackgroundColor(Color.parseColor("#b71c1c"));
                        tampilsoal(ts - 1, sq);
                    }, 1500);
                } else {
                    salah++;
                    Toast.makeText(getApplicationContext(), "Jawaban Salah", Toast.LENGTH_SHORT).show();
                    b3.setBackgroundColor(Color.YELLOW);

                    if (b2.getText().toString().equals(sk.getJawaban())) {
                        b2.setBackgroundColor(Color.GREEN);
                    } else if (b1.getText().toString().equals(sk.getJawaban())) {
                        b1.setBackgroundColor(Color.GREEN);
                    } else if (b4.getText().toString().equals(sk.getJawaban())) {
                        b4.setBackgroundColor(Color.GREEN);
                    }

                    Handler handler = new Handler();
                    handler.postDelayed(() -> {
                        b1.setBackgroundColor(Color.parseColor("#b71c1c"));
                        b2.setBackgroundColor(Color.parseColor("#b71c1c"));
                        b3.setBackgroundColor(Color.parseColor("#b71c1c"));
                        b4.setBackgroundColor(Color.parseColor("#b71c1c"));
                        tampilsoal(ts - 1, sq);
                    }, 1500);
                }total++;
            });

            b4.setOnClickListener(v -> {
                adjustbutton();
                if (b4.getText().toString().equals(sk.getJawaban())) {
                    Toast.makeText(getApplicationContext(), "Jawaban Benar", Toast.LENGTH_SHORT).show();
                    b4.setBackgroundColor(Color.GREEN);
                    Handler handler = new Handler();

                    handler.postDelayed(() -> {
                        benar++;
                        b4.setBackgroundColor(Color.parseColor("#b71c1c"));
                        tampilsoal(ts - 1, sq);
                    }, 1500);
                } else {
                    salah++;
                    Toast.makeText(getApplicationContext(), "Jawaban Salah", Toast.LENGTH_SHORT).show();
                    b4.setBackgroundColor(Color.YELLOW);

                    if (b2.getText().toString().equals(sk.getJawaban())) {
                        b2.setBackgroundColor(Color.GREEN);
                    } else if (b3.getText().toString().equals(sk.getJawaban())) {
                        b3.setBackgroundColor(Color.GREEN);
                    } else if (b1.getText().toString().equals(sk.getJawaban())) {
                        b1.setBackgroundColor(Color.GREEN);
                    }

                    Handler handler = new Handler();
                    handler.postDelayed(() -> {
                        b1.setBackgroundColor(Color.parseColor("#b71c1c"));
                        b2.setBackgroundColor(Color.parseColor("#b71c1c"));
                        b3.setBackgroundColor(Color.parseColor("#b71c1c"));
                        b4.setBackgroundColor(Color.parseColor("#b71c1c"));
                        tampilsoal(ts - 1, sq);
                    }, 1500);
                }total++;
            });
        }

    }

    public void reverseTimer(int detik, final TextView tv){
        ct = new CountDownTimer(detik*1000+1000,1000){

            public void onTick(long milisUntilFinished){
                int detik = (int) (milisUntilFinished/1000);
                int menit = detik/60;
                detik = detik%60;
                tv.setText((String.format(Locale.US,"%02d", menit) + ":" + String.format(Locale.US,"%02d", detik)));
            }

            public void onFinish(){
                tv.setText("Selesai");
                Intent i = new Intent(QuizActivity.this, HasilQuiz.class);
                i.putExtra("total",String.valueOf(total));
                i.putExtra("benar",String.valueOf(benar));
                i.putExtra("salah",String.valueOf(salah));
                startActivity(i);
            }
        };
        ct.start();
    }

    public void adjustbutton() {
        b1.setClickable(false);
        b2.setClickable(false);
        b3.setClickable(false);
        b4.setClickable(false);
    }

    public void adjustbutton2() {
        b1.setClickable(true);
        b2.setClickable(true);
        b3.setClickable(true);
        b4.setClickable(true);
    }

}
