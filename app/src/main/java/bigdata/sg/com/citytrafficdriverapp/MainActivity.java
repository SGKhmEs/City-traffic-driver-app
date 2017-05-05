package bigdata.sg.com.citytrafficdriverapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import bigdata.sg.com.citytrafficdriverapp.Services.QrScanService;
import bigdata.sg.com.citytrafficdriverapp.Services.ServiceGPS;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mButtonStart;
    private Button mButtonStop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButtonStart = (Button) findViewById(R.id.btn_start);
        mButtonStart.setOnClickListener(this);
        mButtonStop = (Button) findViewById(R.id.btn_stop) ;
        mButtonStop.setOnClickListener(this);


        startService(ServiceGPS.newIntent(this));
        QrScanService.setServiceAlarm(this, true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_start:
                QrScanService.setServiceAlarm(this, true);
                break;
            case R.id.btn_stop:
                QrScanService.setServiceAlarm(this, false);
                break;
        }
    }
}
