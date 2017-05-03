package bigdata.sg.com.citytrafficdriverapp.Services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;


import bigdata.sg.com.citytrafficdriverapp.Services.Models.Client;
import bigdata.sg.com.citytrafficdriverapp.Services.Models.GoogleGPSClient;

/**
 * Created by bonar on 5/1/2017.
 */

public class ServiceGPS extends Service {
    private static final String TAG = "ServiceGPS";

    /**
     * Google client
     */
    private Client mClient;

    public static Intent newIntent(Context context)
    {
        return new Intent(context, ServiceGPS.class);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");

        mClient = new GoogleGPSClient(ServiceGPS.this, new GoogleGPSClient.Callbacks() {
            @Override
            public void onConnected(Bundle bundle) {
                Log.d(TAG, "onConnected");
            }

            @Override
            public void onConnectionSuspended(int i) {
                Log.d(TAG, "onConnectionSuspended:");
            }

            @Override
            public void onLocationChanged(Location location) {
                Log.d(TAG, "onLocationChanged: [" + location.getLatitude() + "; " + location.getLongitude() + "]"
                    + " | Speed: " + location.getSpeed() + " m/s | Accuracy: " + location.getAccuracy() + " m");
            }
        });

        mClient.build()
                .connect();

        return START_STICKY;
    }

    /**
     *
     */
    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        mClient.disconnect();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
