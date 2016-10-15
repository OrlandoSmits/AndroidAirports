package nl.orlandosmits.airports;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Orlando Smits on 15-10-2016.
 */

public class AirportAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater mInflator;
    ArrayList mAirportList;

    public AirportAdapter(Context context, LayoutInflater layoutInflater, ArrayList<Airport> airportList)
    {
        mContext = context;
        mInflator = layoutInflater;
        mAirportList = airportList;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
