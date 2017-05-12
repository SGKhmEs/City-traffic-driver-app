package bigdata.sg.com.citytrafficdriverapp.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

import bigdata.sg.com.citytrafficdriverapp.database.Entities.AuthData;
import bigdata.sg.com.citytrafficdriverapp.database.Entities.AuthDataDao;
import bigdata.sg.com.citytrafficdriverapp.database.Entities.DaoMaster;
import bigdata.sg.com.citytrafficdriverapp.database.Entities.DaoSession;
import bigdata.sg.com.citytrafficdriverapp.database.Entities.GpsData;
import bigdata.sg.com.citytrafficdriverapp.database.Entities.GpsDataDao;

/**
 * Created by bonar on 5/12/2017.
 */

public class DaoDatabase implements IDatabase {
    private static final String TAG = "DaoDatabase";

    private static final String DB_NAME = "city-traffic-data-db";

    private Context mContext;
    private DaoSession mDaoSession;

    public DaoDatabase(Context context) {
        mContext = context;
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(mContext, DB_NAME);
        Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
    }

    public void insert(GpsData gpsData)
    {
        GpsDataDao gpsDataDao = mDaoSession.getGpsDataDao();
        gpsDataDao.insert(gpsData);
    }

    public void insert(AuthData authData)
    {
        AuthDataDao authDataDao = mDaoSession.getAuthDataDao();
        authDataDao.insert(authData);
    }

}
