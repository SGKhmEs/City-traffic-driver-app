package bigdata.sg.com.citytrafficdriverapp.Services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import bigdata.sg.com.citytrafficdriverapp.App;
import bigdata.sg.com.citytrafficdriverapp.DataWriter;

public class ServiceSendDB extends IntentService {
    private static final String TAG = "ServiceSendDB";

    public ServiceSendDB() {
        super(TAG);
    }

    public static Intent newIntent(Context context){
        return new Intent(context, ServiceSendDB.class);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "Push GPSDB");

        DataWriter mDataWriter = ((App) getApplication()).getDataWriter();
        mDataWriter.pushGpsDB();
        mDataWriter.pushAuthDB();
    }
}
