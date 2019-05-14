package nitolmotors.sales.com.locationdevice;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;

public class NetworkUtils {
    private static final String TAG = "NetworkUtils";

    private static int TYPE_WIFI = 1;
    private static int TYPE_MOBILE = 2;
    private static int TYPE_NOT_CONNECTED = 0;

    public static int getConnectivityStatus(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

/*
        if (networkInfo != null) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI
                    && networkInfo.getState() == NetworkInfo.State.CONNECTED) {

                return TYPE_WIFI;

            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE
                    && networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                return TYPE_MOBILE;
            }
        }
*/

        if(networkInfo.getType() == ConnectivityManager.TYPE_WIFI){
            Log.d(TAG, "TYPE_WIFI: ");
            // do something
        } else if(networkInfo.getType() == ConnectivityManager.TYPE_MOBILE){
            // check NetworkInfo subtype
            if(networkInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_GPRS){
                Log.d(TAG, "Bandwidth between 100 kbps and below ");
                // Bandwidth between 100 kbps and below
            } else if(networkInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_EDGE){
                Log.d(TAG, "Bandwidth between 50-100 kbps: ");
                // Bandwidth between 50-100 kbps
            } else if(networkInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_EVDO_0){
                Log.d(TAG, "Bandwidth between 400-1000 kbps: ");
                // Bandwidth between 400-1000 kbps
            } else if(networkInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_EVDO_A){
                Log.d(TAG, "Bandwidth between 600-1400 kbps: ");
                // Bandwidth between 600-1400 kbps
            }
        }


        return TYPE_NOT_CONNECTED;
    }

    public static boolean isNetworkConnected(Context context) {
        int networkStatus = getConnectivityStatus(context);
        if (networkStatus == TYPE_WIFI || networkStatus == TYPE_MOBILE) {
            return true;
        } else {
            return false;
        }
    }
}