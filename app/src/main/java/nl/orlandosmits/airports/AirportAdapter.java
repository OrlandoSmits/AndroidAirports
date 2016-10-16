package nl.orlandosmits.airports;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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
        int size = mAirportList.size();
        Log.i("getCount()", "=" + size);
        return size;
    }

    @Override
    public Object getItem(int position) {
        Log.i("getItem()", "");
        return mAirportList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflator.inflate(R.layout.airport_row, null);

            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.icao = (TextView) convertView.findViewById(R.id.icao);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Airport airport = (Airport) mAirportList.get(position);

        viewHolder.name.setText(airport.name);
        viewHolder.icao.setText(airport.icao);

        return convertView;
    }

    private static class ViewHolder {
        public TextView name;
        public TextView icao;
    }
}
