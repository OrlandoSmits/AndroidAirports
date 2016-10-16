package nl.orlandosmits.airports;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView airportList;
    AirportAdapter airportAdapter;
    ArrayList list = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       airportList = (ListView) findViewById(R.id.airportList);

        DatabaseHandler db = new DatabaseHandler(this);

        Bundle extras = getIntent().getExtras();
        String country = extras.getString("AirportCountry");
        Cursor cursor = db.getAirports(country);

        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            Airport airport = new Airport();
            airport.name = cursor.getString(cursor.getColumnIndex("name"));
            airport.icao = cursor.getString(cursor.getColumnIndex("icao"));
            airport.iso_country = cursor.getString(cursor.getColumnIndex("iso_country"));
            airport.latitude = cursor.getDouble(cursor.getColumnIndex("latitude"));
            airport.longitude = cursor.getDouble(cursor.getColumnIndex("longitude"));
            list.add(airport);
        }

        airportAdapter = new AirportAdapter(this, getLayoutInflater(), list);
        Log.i("log", list.toString());
        airportList.setAdapter(airportAdapter);

        airportAdapter.notifyDataSetChanged();

        airportList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("SelectedItem: ", position + "");

        Intent i = new Intent(getApplicationContext(), DetailActivity.class);
        Airport airport = (Airport) this.list.get(position);
        i.putExtra("AirportName",  airport.name);
        i.putExtra("AirportIcao", airport.icao);
        i.putExtra("Lat", airport.latitude);
        i.putExtra("Lon", airport.longitude);

        startActivity(i);

    }
}
