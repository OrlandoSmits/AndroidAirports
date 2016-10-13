package nl.orlandosmits.airports;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        Cursor cursor = db.getAirports();

        while (cursor.moveToNext()) {
            String icao = cursor.getString(cursor.getColumnIndex("icao"));
            String name = cursor.getString(cursor.getColumnIndex("name"));

            Log.i("MainActivity", icao + "," + name);
        }
    }
}
