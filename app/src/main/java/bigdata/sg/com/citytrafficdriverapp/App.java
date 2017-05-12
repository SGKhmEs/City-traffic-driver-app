package bigdata.sg.com.citytrafficdriverapp;

import android.app.Application;

public class App extends Application {
    private static final String TAG = "App";

    private DataWriter mDataWriter;

    @Override
    public void onCreate() {
        super.onCreate();

        mDataWriter = new DBRestWriter(this);
    }

    public DataWriter getDataWriter()
    {
        return mDataWriter;
    }

}
