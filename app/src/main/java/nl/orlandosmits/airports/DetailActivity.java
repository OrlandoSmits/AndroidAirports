package nl.orlandosmits.airports;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();
        String airportName = extras.getString("AirportName");
        String airportIcao = extras.getString("AirportIcao");

        TextView detailName = (TextView) findViewById(R.id.detailName);
        detailName.setText(airportName);

        TextView detailIcao = (TextView) findViewById(R.id.detailIcao);
        detailIcao.setText(airportIcao);

    }
}
