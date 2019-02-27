package com.sera5.narawara;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bugsnag.android.Bugsnag;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class ViewArtikelActivity extends BaseActivity {

    private TextView title, published_date, update_date, author, sumber;
    private SimpleDraweeView img;
    private HtmlTextView htmlTextView;
    String view_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_artikel);
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss",Locale.US);
        SimpleDateFormat df2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss",Locale.US);

        ActionBar actionBar = getActionBar();
        android.support.v7.app.ActionBar actionBar1 = getSupportActionBar();

        if(actionBar1!=null) {
            actionBar1.setDisplayHomeAsUpEnabled(true);
            setTitle("Lihat Artikel");
        } else {
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
            setTitle("Lihat Artikel");
        }

        Bundle b = getIntent().getExtras();
        if (b != null) {
            view_id = b.getString("news_id");
            System.out.println("Berita: " + view_id);
        }

        htmlTextView = findViewById(R.id.va_txt_content);
        title = findViewById(R.id.va_title);
        published_date = findViewById(R.id.va_txt_date);
        update_date = findViewById(R.id.va_txt_update);
        author = findViewById(R.id.va_txt_author);
        img = findViewById(R.id.va_img);
        sumber = findViewById(R.id.va_sumber);

        update_date.setVisibility(View.INVISIBLE);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference dbref = null;

        if (view_id != null) {
            dbref = database.child("entry").child(view_id);
        } else {
            Bugsnag.notify(new NullPointerException("Null attack in ViewArtikelActivity!"));
        }

        if (dbref != null) {
            dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    System.out.println(dataSnapshot);
                    String _title = dataSnapshot.child("title").getValue(String.class);
                    final String _image_url = dataSnapshot.child("gambar").getValue(String.class);
                    String _sumber = dataSnapshot.child("sumber").getValue(String.class);
                    String _content = dataSnapshot.child("content").getValue(String.class);
                    Object _createdAt = dataSnapshot.child("createdAt").getValue();
                    String _author = dataSnapshot.child("author").getValue(String.class);

                    title.setText(_title);
                    _sumber = "Sumber artikel: " + _sumber;

                    if (_createdAt != null) {
                        try {
                            Date date = df.parse(String.valueOf(_createdAt));
                            published_date.setText(df2.format(date));
                        } catch (ParseException e) {
                            published_date.setText(_createdAt.toString());
                            throw new RuntimeException("Failed to parse date: ", e);
                        }
                    }

                    author.setText(_author);
                    sumber.setText(Html.fromHtml(_sumber));

                    if (_content != null) {
                        htmlTextView.setHtml(_content, new HtmlHttpImageGetter(htmlTextView));
                    }

                    if(_image_url!=null)
                    img.setImageURI(_image_url);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } else {
            Bugsnag.notify(new NullPointerException("Null attack in ViewArtikelActivity.dbref!"));
            finish();
        }
    }


}
