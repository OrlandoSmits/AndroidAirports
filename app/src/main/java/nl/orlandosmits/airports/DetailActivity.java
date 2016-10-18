package nl.orlandosmits.airports;

import android.graphics.Color;
import android.location.Location;
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
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

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

    static final LatLng Schiphol = new LatLng(52.3094593, 4.7600949);
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

        Double avgLat = (lat + Schiphol.latitude)/ 2;
        Double avgLon = (lon + Schiphol.longitude) /2;
        LatLng avgCoordinates = new LatLng(avgLat, avgLon);

        Location schiphol = new Location("");
        schiphol.setLatitude(Schiphol.latitude);
        schiphol.setLongitude(Schiphol.longitude);

        Location current = new Location("");
        current.setLatitude(lat);
        current.setLongitude(lon);

        float distanceInMeters = schiphol.distanceTo(current);

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(Schiphol);
        builder.include(airportLocation);
        LatLngBounds bounds = builder.build();
        googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 200));

        Marker currentAirport = this.googleMap.addMarker(new MarkerOptions()
        .position(airportLocation)
        .title(airportName)
        .snippet(airportIcao));

        Marker airportSchiphol = this.googleMap.addMarker(new MarkerOptions()
        .position(Schiphol)
        .title("Schiphol")
        .snippet("Schiphol"));

        this.googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        Polyline line = googleMap.addPolyline(new PolylineOptions()
        .add(Schiphol, airportLocation)
        .width(5)
        .color(Color.BLUE)
        .geodesic(true));


        mapView.onResume();

    }
}
