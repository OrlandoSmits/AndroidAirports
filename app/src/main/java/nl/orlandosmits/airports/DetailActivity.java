package nl.orlandosmits.airports;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap googleMap;
    Airport airport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mapView = (MapView) findViewById(R.id.airportMap);
        mapView.onCreate(savedInstanceState);

        if(mapView != null){
            mapView.getMapAsync(this);
        }

        Bundle extras = getIntent().getExtras();
        String airportName = extras.getString("AirportName");
        String airportIcao = extras.getString("AirportIcao");

        TextView detailName = (TextView) findViewById(R.id.detailName);
        detailName.setText(airportName);

        TextView detailIcao = (TextView) findViewById(R.id.detailIcao);
        detailIcao.setText(airportIcao);

    }

    static final LatLng AVANS = new LatLng(51.5719, 4.7683);
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.googleMap.getUiSettings().setMyLocationButtonEnabled(true);

        MapsInitializer.initialize(this);

        Bundle extras = getIntent().getExtras();
        Double lat = extras.getDouble("Lat");
        Double lon = extras.getDouble("Lon");
        String airportName = extras.getString("AirportName");
        LatLng airportLocation = new LatLng(lat, lon);
        String airportIcao = extras.getString("AirportIcao");

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(airportLocation, 13);
        this.googleMap.animateCamera(cameraUpdate);

        Marker marker = this.googleMap.addMarker(new MarkerOptions()
        .position(airportLocation)
        .title(airportName)
        .snippet(airportIcao));

        this.googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        PolygonOptions polygonOptions = new PolygonOptions()
                .add(new LatLng(51.586662, 4.791969),
                        new LatLng( 51.584434, 4.793528),
                        new LatLng( 51.587474, 4.795993),
                        new LatLng(51.586662, 4.791969));
        this.googleMap.addPolygon(polygonOptions);


        mapView.onResume();

    }
}
