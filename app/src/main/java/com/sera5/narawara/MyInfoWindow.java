package com.sera5.narawara;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.firebase.database.DataSnapshot;

import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.infowindow.InfoWindow;

public class MyInfoWindow extends InfoWindow {
     private String judul,description_,kategori,lon,lat,gambar;
     private String rating;
     private DataSnapshot ds;
     private MapView mapView;

    public MyInfoWindow(int layoutResId, MapView mapView, DataSnapshot data) {
        super(layoutResId, mapView);

        this.ds = data;
        this.judul = ds.child("nama").getValue(String.class);
        this.description_ = ds.child("lengkap").getValue(String.class);
        this.lon = ds.child("longitude").getValue(String.class);
        this.lat = ds.child("latitude").getValue(String.class);
        this.gambar = ds.child("foto").getValue(String.class);
        this.mapView = mapView;
        this.kategori = "";


    }
    public void onClose() {
        this.close();
    }

    public void onOpen(Object arg0) {
        LinearLayout layout =  mView.findViewById(R.id.bubble_layout);
        TextView title = mView.findViewById(R.id.bubble_title);
        Button details = mView.findViewById(R.id.barang_details);
        SimpleDraweeView gmb = mView.findViewById(R.id.gmb_pahlawan);

        if(!gambar.isEmpty()) {
            Uri imageUri = Uri.parse(gambar);
            gmb.setImageURI(imageUri);
        }

        title.setText(judul);
        details.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), PahlawanDetailsActivity.class);
            intent.putExtra("view_id", ds.getKey());
            v.getContext().startActivity(intent);
            onClose();
        });

        layout.setOnClickListener(v -> onClose());
        InfoWindow.closeAllInfoWindowsOn(this.mapView);
    }
}