package bigdata.sg.com.citytrafficdriverapp.Services;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import bigdata.sg.com.citytrafficdriverapp.QrCodeScannerActivity;

public class QrScanService extends IntentService {
    private static final String TAG = "QrScanService";
//    private static final long SCAN_INTERVAL = 360000 * 2; // 2 hours
    private static final long SCAN_INTERVAL = 1000; // 60 seconds min value

    public static Intent newIntent(Context context){
        return new Intent(context, QrScanService.class);
    }

    public QrScanService() {
        super(TAG);
    }

    public static void setServiceAlarm(Context context, boolean isOn){
        Intent scanQrIntent = QrScanService.newIntent(context);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, scanQrIntent, 0);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (isOn){
            alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME,
                    SystemClock.elapsedRealtime(), SCAN_INTERVAL, pendingIntent);
        }else {
            alarmManager.cancel(pendingIntent);
            pendingIntent.cancel();
        }

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
