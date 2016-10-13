package nl.orlandosmits.airports;

/**
 * Created by Orlando Smits on 13-10-2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class DatabaseHandler extends SQLiteAssetHelper {

    private static final String DB_NAME = "airports.db";
    private static final int DB_VERSION = 1;

    public DatabaseHandler(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    public Cursor getAirports() {
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT icao, name FROM airports WHERE iso_country = \"NL\"";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        return c;
    }
}
