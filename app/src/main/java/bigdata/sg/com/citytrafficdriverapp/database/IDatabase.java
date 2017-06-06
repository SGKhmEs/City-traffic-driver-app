package bigdata.sg.com.citytrafficdriverapp.database;

import java.util.List;

import bigdata.sg.com.citytrafficdriverapp.database.Entities.AuthData;
import bigdata.sg.com.citytrafficdriverapp.database.Entities.GpsData;

public interface IDatabase {
    void insert(GpsData gpsData);
    void insert(AuthData authData);
    List<GpsData> getGpsRecords(int limit);
    List<AuthData> getAuthRecords(int limit);
    void deleteGpsRecord(long id);
    void deleteAuthRecord(long id);
}
