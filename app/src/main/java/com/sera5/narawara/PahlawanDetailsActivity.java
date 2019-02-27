package com.sera5.narawara;

import android.app.ActionBar;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PahlawanDetailsActivity extends BaseActivity {
    String view_id;
    SimpleDateFormat df,df2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pahlawan_details);

        df = new SimpleDateFormat("yyyy-MM-dd",Locale.US);
        df2 = new SimpleDateFormat("dd-MM-yyyy",Locale.US);

        ActionBar actionBar = getActionBar();
        android.support.v7.app.ActionBar actionBar1 = getSupportActionBar();

        if(actionBar1!=null) {
            actionBar1.setDisplayHomeAsUpEnabled(true);
            setTitle("Lihat Profil");
        } else {
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
            setTitle("Lihat Profil");
        }

        Bundle b = getIntent().getExtras();
        if (b != null) {
            view_id = b.getString("view_id");
           // System.out.println("Berita: " + view_id);


            TextView lahir = findViewById(R.id.geboren);
            TextView nama = findViewById(R.id.p_name);
            TextView info = findViewById(R.id.deskripsi_pahlawan);
            SimpleDraweeView gmb = findViewById(R.id.pahlawan_gmb_);

            DatabaseReference dr = FirebaseDatabase.getInstance().getReference().child("pahlawan").child(view_id);

            dr.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String nama__ = dataSnapshot.child("nama").getValue(String.class);
                    String lahir__ = dataSnapshot.child("lahir").getValue(String.class);
                    String info__ = dataSnapshot.child("lengkap").getValue(String.class);
                    String foto__ = dataSnapshot.child("foto").getValue(String.class);

                    if(nama__==null) nama__ = "";
                    if(lahir__==null) lahir__ = "";
                    if(info__==null) info__ = "";

                    if(foto__!=null) {
                        gmb.setImageURI(foto__);
                    }

                    nama.setText(nama__);
                    try {
                        Date dd = df.parse(lahir__);
                        lahir.setText(df2.format(dd));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    info.setText(info__);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }
}
