package nitolmotors.sales.com.locationdevice;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    Button btn_getLocation;
    TextView txt_lat, txt_lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_lat = findViewById(R.id.txt_lat);
        txt_lng = findViewById(R.id.txt_lng);

        btn_getLocation = findViewById(R.id.btn_getLocation);
        btn_getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callGPS();
            }
        });


    }

    private void callGPS() {

        GPSUtils gpsUtils = new GPSUtils(MainActivity.this);
        Location location = gpsUtils.getLocationTest(MainActivity.this);

        txt_lat.setText("Latitude: " + location.getLatitude());
        txt_lng.setText("Longitude: " + location.getLongitude());


    }


}
