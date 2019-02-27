package com.sera5.narawara.Fragments.HomeFragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sera5.narawara.MyFragment;
import com.sera5.narawara.MyInfoWindow;
import com.sera5.narawara.R;
import com.sera5.narawara.MapMarker;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.constants.OpenStreetMapTileProviderConstants;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.infowindow.InfoWindow;

import java.io.File;
import java.util.ArrayList;

public class MapFragment extends MyFragment {
    MapView map = null;
    Button bt_reset = null;
    IMapController mapController = null;
    ArrayList<MapMarker> mk = new ArrayList<>();

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home_mapview, container, false);

        org.osmdroid.config.IConfigurationProvider osmConf = org.osmdroid.config.Configuration.getInstance();
        File basePath = new File(container.getContext().getCacheDir().getAbsolutePath(), "osmdroid");
        osmConf.setOsmdroidBasePath(basePath);
        File tileCache = new File(osmConf.getOsmdroidBasePath().getAbsolutePath(), "tile");
        osmConf.setOsmdroidTileCache(tileCache);
        Drawable marker = this.getResources().getDrawable(R.drawable.ic_chat_bubble);

        map = (MapView) rootView.findViewById(R.id.mainMap);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(false);
        map.setMultiTouchControls(true);
        mapController = map.getController();
        initLists(map,marker);

        Button mzo = rootView.findViewById(R.id.map_ZoomOut);
        Button mzi = rootView.findViewById(R.id.map_ZoomIn);
        Button mr = rootView.findViewById(R.id.map_reset);

        mzo.setOnClickListener(v -> mapController.zoomOut());
        mzi.setOnClickListener(v -> mapController.zoomIn());
        mr.setOnClickListener(v -> reset());

        reset();
        return rootView;
    }

    private void initLists(MapView map, Drawable marker) {
        DatabaseReference mref = FirebaseDatabase.getInstance().getReference("pahlawan");

        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                double la = 0,lo = 0;

                if(mk!=null && mk.size()>0) {
                    map.getOverlays().removeAll(mk);
                    mk.clear();
                }

                for (DataSnapshot item : dataSnapshot.getChildren()) {

                    try {
                        String lon  = item.child("longitude").getValue(String.class);
                        String lat  = item.child("latitude").getValue(String.class);
                        if(lon!=null&&lat!=null) {
                            la = Double.parseDouble(lat);
                            lo = Double.parseDouble(lon);
                        }
                    } catch (ClassCastException e) {
                        //Toast.makeText(getBaseContext(), nama+" tidak dapat diproses.", Toast.LENGTH_SHORT).show();
                    } catch (NumberFormatException e) {
                        //Toast.makeText(getBaseContext(), nama+" tidak dapat diproses.", Toast.LENGTH_SHORT).show();
                    }catch (NullPointerException ignored) {

                    } finally {
                        GeoPoint z  = new GeoPoint(la, lo);
                        MapMarker i    = new MapMarker(map,"tempat");

                        i.setPosition(z);
                        i.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                        i.setIcon(marker);

                        InfoWindow infoWindow = new MyInfoWindow(R.layout.layout_bubble, map, item);
                        i.setInfoWindow(infoWindow);

                        mk.add(i);
                    }
                }
                map.getOverlays().addAll(mk);
                map.invalidate();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void reset() {
        mapController.setZoom(4.8);
        GeoPoint startPoint = new GeoPoint(-0.5014662,117.0973128);
        mapController.animateTo(startPoint);
    }

    @Override
    public void onResume(){
        super.onResume();
        map.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
        map.onPause();
    }
}