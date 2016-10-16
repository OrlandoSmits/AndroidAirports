package nl.orlandosmits.airports;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Orlando Smits on 16-10-2016.
 */

public class AirportCountryAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater mInflater;
    ArrayList mCountryArrayList;

    public AirportCountryAdapter(Context context, LayoutInflater layoutInflater, ArrayList<Airport> countryList)
    {
        mContext = context;
        mInflater = layoutInflater;
        mCountryArrayList = countryList;
    }

    @Override
    public int getCount() {
        int size = mCountryArrayList.size();
        Log.i("getCount()", "=" + size);
        return size;
    }

    @Override
    public Object getItem(int position) {
        return mCountryArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.country_row, null);

            viewHolder = new ViewHolder();
            viewHolder.country = (TextView) convertView.findViewById(R.id.country);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Airport airport = (Airport) mCountryArrayList.get(position);

        Locale loc = new Locale("", airport.iso_country);
        viewHolder.country.setText(loc.getDisplayCountry());

        return convertView;
    }

    private static class ViewHolder {
        public TextView country;
    }
}
