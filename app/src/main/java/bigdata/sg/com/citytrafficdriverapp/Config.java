package bigdata.sg.com.citytrafficdriverapp;

import bigdata.sg.com.citytrafficdriverapp.Services.Helpers.ServiceAlarmManager;

/**
 * Created by bonar on 5/18/2017.
 */

public class Config {
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final long SERVICEGPS_WAKEUP_INTERVAL = ServiceAlarmManager.MINS_30;
    public static final long AUTH_INTERVAL = ServiceAlarmManager.HOURS_2;
}
