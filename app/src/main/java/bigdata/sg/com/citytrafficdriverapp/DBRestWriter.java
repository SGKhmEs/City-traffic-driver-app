package bigdata.sg.com.citytrafficdriverapp;

import android.content.Context;

import bigdata.sg.com.citytrafficdriverapp.database.DaoDatabase;
import bigdata.sg.com.citytrafficdriverapp.database.Entities.AuthData;
import bigdata.sg.com.citytrafficdriverapp.database.Entities.GpsData;
import bigdata.sg.com.citytrafficdriverapp.database.IDatabase;

public class DBRestWriter implements DataWriter {
    private static final String TAG = "DBRestWriter";

    private IDatabase mDatabase;

    public DBRestWriter(Context context) {
        mDatabase = new DaoDatabase(context);
    }

    @Override
    public void write(GpsData data) {
        mDatabase.insert(data);

        //TODO Should post data on server or to database if internet is unavailable
    }

    @Override
    public void write(AuthData data) {
        mDatabase.insert(data);

        //TODO Should post data on server or to database if internet is unavailable
    }
}
