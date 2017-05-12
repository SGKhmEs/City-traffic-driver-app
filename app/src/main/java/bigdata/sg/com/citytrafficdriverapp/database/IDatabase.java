package bigdata.sg.com.citytrafficdriverapp.database;

import bigdata.sg.com.citytrafficdriverapp.database.Entities.AuthData;
import bigdata.sg.com.citytrafficdriverapp.database.Entities.GpsData;

public interface IDatabase {
    void insert(GpsData gpsData);
    void insert(AuthData authData);
}
