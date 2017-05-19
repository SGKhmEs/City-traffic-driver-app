package bigdata.sg.com.citytrafficdriverapp.Services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import bigdata.sg.com.citytrafficdriverapp.App;
import bigdata.sg.com.citytrafficdriverapp.Config;
import bigdata.sg.com.citytrafficdriverapp.DataWriter;
import bigdata.sg.com.citytrafficdriverapp.Events.AuthEvent;
import bigdata.sg.com.citytrafficdriverapp.QueryPreferences;
import bigdata.sg.com.citytrafficdriverapp.Services.Models.Client;
import bigdata.sg.com.citytrafficdriverapp.Services.Models.GoogleGPSClient;
import bigdata.sg.com.citytrafficdriverapp.Utils.DateProvider;
import bigdata.sg.com.citytrafficdriverapp.database.Entities.AuthData;
import bigdata.sg.com.citytrafficdriverapp.database.Entities.GpsData;

public class ServiceGPS extends Service {
    private static final String TAG = "ServiceGPS";

    /**
     * Google client
     */
    private Client mClient;

    private DataWriter mDataWriter;

    private String qrKey;

    public static Intent newIntent(Context context) {
        return new Intent(context, ServiceGPS.class);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");

        mDataWriter = ((App) getApplication()).getDataWriter();

        qrKey = QueryPreferences.getQrValue(this);

        if(qrKey != null)
            startClient();


        return START_STICKY;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAuthEvent(AuthEvent authEvent) {
        if(authEvent.isLogin == AuthData.LOGIN) {
            Log.d(TAG, "Login");
            qrKey = authEvent.qrValue;
            if(mClient == null)
                startClient();
        } else {
            Log.d(TAG, "Logout");
            if(mClient != null)
                mClient.disconnect();
        }
    }

    private void startClient() {
        if(mClient != null)
            mClient.disconnect();

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

                String currentDate = DateProvider.getCurrentDate(Config.DATE_FORMAT);
                GpsData gpsData = new GpsData(location, currentDate, qrKey);

                mDataWriter.write(gpsData);
            }
        });

        mClient.build()
                .connect();
    }

    /**
     *
     */
    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        EventBus.getDefault().unregister(this);
        if(mClient != null)
            mClient.disconnect();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
