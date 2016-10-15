package nl.orlandosmits.airports;

/**
 * Created by Orlando Smits on 13-10-2016.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class DatabaseHandler extends SQLiteAssetHelper {

    private static final String DB_NAME = "airports.sqlite";
    private static final int DB_VERSION = 1;

    public DatabaseHandler(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    public Cursor getAirports() {
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT icao, name FROM airports WHERE iso_country = \"NL\" ORDER BY name;";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        db.close();
        return c;
    }

    public Cursor getCountrys() {
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT iso_country FROM airports GROUP BY iso_country ORDER BY iso_country;";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        db.close();
        return c;
    }
}
