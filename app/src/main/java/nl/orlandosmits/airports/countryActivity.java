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

public class countryActivity extends AppCompatActivity {
    private ListView countryList;
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
            String country = cursor.getString(cursor.getColumnIndex("iso_country"));
            Airport airport = new Airport();
            airport.iso_country = country;
            list.add(country);
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, list);
        countryList.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        countryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                Airport airport = list.get(position);

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        Log.d("SelectedItem: ", position + "");

        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        this.countryList.get(position);
    }
}
