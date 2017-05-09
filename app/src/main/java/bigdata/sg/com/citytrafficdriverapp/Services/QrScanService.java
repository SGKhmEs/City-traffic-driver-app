package bigdata.sg.com.citytrafficdriverapp.Services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import bigdata.sg.com.citytrafficdriverapp.QrCodeScannerActivity;

public class QrScanService extends IntentService {
    private static final String TAG = "QrScanService";

    public static Intent newIntent(Context context){
        return new Intent(context, QrScanService.class);
    }

    public QrScanService() {
        super(TAG);
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        super.onStart(intent, startId);
        Intent scanQrIntent = new Intent(this, QrCodeScannerActivity.class);
        scanQrIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(scanQrIntent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i(TAG, "Received an intent: " + intent);
    }
}
