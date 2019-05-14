package nitolmotors.sales.com.locationdevice;

import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    Context context;

    Button btn_getLocation;
    TextView txt_lat, txt_lng;
    ConnectivityManager connectivityManager;

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
                if(NetworkUtils.isNetworkConnected(MainActivity.this)){
                    Log.d(TAG, "Connected: ");
                } else {
                    Log.d(TAG, "Not connected: ");
                }
                //checkNetworkType();

            }
        });


    }

    private void callGPS() {

        GPSUtils gpsUtils = new GPSUtils(MainActivity.this);
        Location location = gpsUtils.getLocationTest(MainActivity.this);

        txt_lat.setText("Latitude: " + location.getLatitude());
        txt_lng.setText("Longitude: " + location.getLongitude());
    }

    private void checkNetworkType(){


        connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();

        if(info.getType() == ConnectivityManager.TYPE_WIFI){
            // do something
        } else if(info.getType() == ConnectivityManager.TYPE_MOBILE){
            // check NetworkInfo subtype
            if(info.getSubtype() == TelephonyManager.NETWORK_TYPE_GPRS){
                // Bandwidth between 100 kbps and below
            } else if(info.getSubtype() == TelephonyManager.NETWORK_TYPE_EDGE){
                // Bandwidth between 50-100 kbps
            } else if(info.getSubtype() == TelephonyManager.NETWORK_TYPE_EVDO_0){
                // Bandwidth between 400-1000 kbps
            } else if(info.getSubtype() == TelephonyManager.NETWORK_TYPE_EVDO_A){
                // Bandwidth between 600-1400 kbps
            }

            // Other list of various subtypes you can check for and their bandwidth limits
            // TelephonyManager.NETWORK_TYPE_1xRTT       ~ 50-100 kbps
            // TelephonyManager.NETWORK_TYPE_CDMA        ~ 14-64 kbps
            // TelephonyManager.NETWORK_TYPE_HSDPA       ~ 2-14 Mbps
            // TelephonyManager.NETWORK_TYPE_HSPA        ~ 700-1700 kbps
            // TelephonyManager.NETWORK_TYPE_HSUPA       ~ 1-23 Mbps
            // TelephonyManager.NETWORK_TYPE_UMTS        ~ 400-7000 kbps
            // TelephonyManager.NETWORK_TYPE_UNKNOWN     ~ Unknown

        }
    }


}
