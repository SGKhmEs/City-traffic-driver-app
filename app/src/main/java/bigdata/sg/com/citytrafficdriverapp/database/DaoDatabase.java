package bigdata.sg.com.citytrafficdriverapp.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import bigdata.sg.com.citytrafficdriverapp.database.Entities.AuthData;
import bigdata.sg.com.citytrafficdriverapp.database.Entities.AuthDataDao;
import bigdata.sg.com.citytrafficdriverapp.database.Entities.DaoMaster;
import bigdata.sg.com.citytrafficdriverapp.database.Entities.DaoMaster.DevOpenHelper;
import bigdata.sg.com.citytrafficdriverapp.database.Entities.DaoSession;
import bigdata.sg.com.citytrafficdriverapp.database.Entities.GpsData;
import bigdata.sg.com.citytrafficdriverapp.database.Entities.GpsDataDao;

public class DaoDatabase implements IDatabase {
    private static final String TAG = "DaoDatabase";

    private static final String DB_NAME = "city-traffic-data-db";

    private Context mContext;
    private DaoSession mDaoSession;

    public DaoDatabase(Context context) {
        mContext = context;
        DevOpenHelper helper = new DevOpenHelper(mContext, DB_NAME);
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

    /**
     * Get all record from GpsData table
     * @param limit number of records to grab. If < 0 grabs all records
     * @return
     */
    public List<GpsData> getGpsRecords(int limit)
    {
        GpsDataDao gpsDataDao = mDaoSession.getGpsDataDao();
        QueryBuilder<GpsData> qb = gpsDataDao.queryBuilder()
                .orderAsc(GpsDataDao.Properties.Time);

        if(limit > 0)
            qb.limit(limit);

        return qb.list();
    }

}
