package nl.orlandosmits.airports;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class countryActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView countryList;
    AirportCountryAdapter countryAdapter;
    ArrayList list = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);


        countryList = (ListView) findViewById(R.id.countrylist);

        DatabaseHandler db = new DatabaseHandler(this);
        Cursor cursor = db.getCountrys();

        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            Airport airport = new Airport();
            airport.iso_country = cursor.getString(cursor.getColumnIndex("iso_country"));
            list.add(airport);
        }

        countryAdapter = new AirportCountryAdapter(this, getLayoutInflater(), list);
        countryList.setAdapter(countryAdapter);

        countryAdapter.notifyDataSetChanged();

        countryList.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        Log.d("SelectedItem: ", position + "");

        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        Airport airport = (Airport) this.list.get(position);
        i.putExtra("AirportCountry", airport.iso_country);

        startActivity(i);
    }

}