package com.sera5.narawara;

import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class MapMarker extends Marker {
    private String kategori;

    public MapMarker(MapView mapView, String kategori) {
        super(mapView);
        this.kategori = kategori;
    }

    @Override
    public void showInfoWindow() {
        if (mInfoWindow == null)
            return;

        int markerWidth = 0, markerHeight = 0;
        markerWidth = mIcon.getIntrinsicWidth();
        markerHeight = mIcon.getIntrinsicHeight();

        int offsetX = (int)(mIWAnchorU*markerWidth) - (int)(mAnchorU*markerWidth);
        int offsetY = (int)(mIWAnchorV*markerHeight) - (int)(mAnchorV*markerHeight);

        mInfoWindow.open(this, mPosition, offsetX, offsetY);
    }
}
