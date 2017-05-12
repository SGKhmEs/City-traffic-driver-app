package bigdata.sg.com.citytrafficdriverapp;

import bigdata.sg.com.citytrafficdriverapp.database.Entities.AuthData;
import bigdata.sg.com.citytrafficdriverapp.database.Entities.GpsData;

public interface DataWriter {
    void write(GpsData data);
    void write(AuthData data);
}
