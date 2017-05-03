package bigdata.sg.com.citytrafficdriverapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import bigdata.sg.com.citytrafficdriverapp.Services.ServiceGPS;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(ServiceGPS.newIntent(this));
    }
}
