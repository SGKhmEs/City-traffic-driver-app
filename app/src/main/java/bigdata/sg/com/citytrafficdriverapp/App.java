package bigdata.sg.com.citytrafficdriverapp;

import android.app.Application;

import org.greenrobot.greendao.database.Database;

import bigdata.sg.com.citytrafficdriverapp.database.DaoMaster;
import bigdata.sg.com.citytrafficdriverapp.database.DaoMaster.DevOpenHelper;
import bigdata.sg.com.citytrafficdriverapp.database.DaoSession;

public class App extends Application {
    private static final String TAG = "App";

    private static final String DB_NAME = "city-traffic-data-db";

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DevOpenHelper helper = new DevOpenHelper(this, DB_NAME);
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
