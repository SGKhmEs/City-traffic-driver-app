package bigdata.sg.com.citytrafficdriverapp.Services.Models;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class GoogleGPSClient implements Client {
    private static final String TAG = "GoogleGPSClient";

    private Context mContext;
    private Callbacks mCallbacks;
    private GoogleApiClient mClient;

    private int mPriority = LocationRequest.PRIORITY_HIGH_ACCURACY;

    /**
     * Interval in milliseconds
     */
    private long mInterval = 1000;

    public GoogleGPSClient(Context mContext) {
        this.mContext = mContext;
    }

    public GoogleGPSClient(Context mContext, Callbacks mCallbacks) {
        this.mContext = mContext;
        this.mCallbacks = mCallbacks;
    }

    public GoogleGPSClient setCallbacks(Callbacks mCallbacks) {
        this.mCallbacks = mCallbacks;
        return this;
    }

    public boolean isConnected()
    {
        return mClient!= null && mClient.isConnected();
    }

    public boolean isConnecting()
    {
        return mClient != null && mClient.isConnecting();
    }

    @Override
    public GoogleGPSClient setPriority(int mPriority)
    {
        this.mPriority = mPriority;
        return this;
    }

    @Override
    public Client setInterval(long mInterval) {
        this.mInterval = mInterval;
        return this;
    }

    @Override
    public Client build()
    {
        mClient = new GoogleApiClient.Builder(mContext)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(@Nullable Bundle bundle) {
                        if(mCallbacks != null)
                            mCallbacks.onConnected(bundle);

                        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION)
                                != PackageManager.PERMISSION_GRANTED
                                && ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION)
                                != PackageManager.PERMISSION_GRANTED) {

                            // TODO: request permission

                            Log.d(TAG, "No permission");
                            return;
                        }

                        LocationRequest request = LocationRequest.create()
                                .setPriority(mPriority)
                                .setInterval(mInterval);

                        LocationServices.FusedLocationApi
                                .requestLocationUpdates(mClient, request, new LocationListener() {
                                    @Override
                                    public void onLocationChanged(Location location) {
                                        if(mCallbacks != null)
                                            mCallbacks.onLocationChanged(location);
                                    }
                                });
                    }

                    @Override
                    public void onConnectionSuspended(int i) {
                        if(mCallbacks != null)
                            mCallbacks.onConnectionSuspended(i);
                    }
                }).build();
        return this;
    }

    @Override
    public int getPriority() {
        return mPriority;
    }

    @Override
    public long getInterval() {
        return mInterval;
    }

    @Override
    public void connect() {
        mClient.connect();
    }

    @Override
    public void disconnect() {
        mClient.disconnect();
    }

    public interface Callbacks {
        void onConnected(Bundle bundle);
        void onConnectionSuspended(int i);

        /**
         *
         * @param location
         */
        void onLocationChanged(Location location);
    }
}
