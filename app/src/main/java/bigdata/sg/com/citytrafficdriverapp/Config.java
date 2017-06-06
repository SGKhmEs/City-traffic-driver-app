package bigdata.sg.com.citytrafficdriverapp;

import bigdata.sg.com.citytrafficdriverapp.Services.Helpers.ServiceAlarmManager;


public class Config {
    public static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";


    public static final long SERVICEGPS_WAKEUP_INTERVAL = ServiceAlarmManager.MINS_30;
    public static final long AUTH_INTERVAL = ServiceAlarmManager.HOURS_2;
    public static final long SERVICE_SENDDB_INTERVAL = ServiceAlarmManager.MINS_30;

    public static final int DB_PULL_LIMIT = 100;
}
