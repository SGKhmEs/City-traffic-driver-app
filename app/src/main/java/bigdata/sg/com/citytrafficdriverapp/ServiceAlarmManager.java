package bigdata.sg.com.citytrafficdriverapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

/**
 * Created by bonar on 5/9/2017.
 */

public class ServiceAlarmManager {
    private static final String TAG = "ServiceAlarmManager";

    public static final long HOURS_2 = 1000 * 60 * 60 * 2; //2 hours
    public static final long MINS_30 = 1000 * 60 * 30; //30 mins
    public static final long MINS_1 = 1000 * 60; //1 min

    public static void setServiceAlarm(Context context, long interval, Intent intent, boolean isOn) {
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager)
                context.getSystemService(Context.ALARM_SERVICE);

        if (isOn) {
            alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME,
                    SystemClock.elapsedRealtime(), interval, pendingIntent);
        } else {
            alarmManager.cancel(pendingIntent);
            pendingIntent.cancel();
        }
    }

    public static boolean isServiceAlarmOn(Context context, Intent intent) {
        PendingIntent pendingIntent = PendingIntent
                .getService(context, 0, intent, PendingIntent.FLAG_NO_CREATE);
        return pendingIntent != null;
    }

}
