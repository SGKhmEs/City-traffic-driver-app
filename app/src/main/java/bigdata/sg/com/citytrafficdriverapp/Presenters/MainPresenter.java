package bigdata.sg.com.citytrafficdriverapp.Presenters;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import bigdata.sg.com.citytrafficdriverapp.Activities.MainActivity;
import bigdata.sg.com.citytrafficdriverapp.Config;
import bigdata.sg.com.citytrafficdriverapp.R;
import bigdata.sg.com.citytrafficdriverapp.Services.Helpers.ServiceAlarmManager;
import bigdata.sg.com.citytrafficdriverapp.Services.QrScanService;
import bigdata.sg.com.citytrafficdriverapp.Services.ServiceGPS;
import bigdata.sg.com.citytrafficdriverapp.database.DaoDatabase;

public class MainPresenter implements View.OnClickListener{
    private static final String TAG = "MainPresenter";

    private static final int REQUEST_PERMISSIONS = 1;

    private MainActivity mActivity;

    public MainPresenter(MainActivity mActivity)
    {
        this.mActivity = mActivity;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mainActivityButtonControl:
                onMainActivityButtonControlClick();
                break;
        }
    }

    private void onMainActivityButtonControlClick()
    {
        if(!checkPermissions())
        {
            setServicesState(false);
            requestPermissions();
            return;
        }

        boolean state = getCurrentServiceState();

        setServicesState(!state);

        validate();
    }


    public void validate()
    {
        boolean state = getCurrentServiceState();
        mActivity.changeButtonState(state);

        //TableGPS content
        Log.d(TAG, new DaoDatabase(mActivity).getGpsRecords(0).toString());
    }

    private boolean getCurrentServiceState()
    {
        return  ServiceAlarmManager.isServiceAlarmOn(mActivity, ServiceGPS.newIntent(mActivity))
                && ServiceAlarmManager.isServiceAlarmOn(mActivity, QrScanService.newIntent(mActivity));
    }

    private void setServicesState(boolean state)
    {
        ServiceAlarmManager.setServiceAlarm(mActivity, Config.SERVICEGPS_WAKEUP_INTERVAL, ServiceGPS.newIntent(mActivity), state);
        ServiceAlarmManager.setServiceAlarm(mActivity, Config.AUTH_INTERVAL, QrScanService.newIntent(mActivity), state);
        if(!state)
        {
            mActivity.stopService(ServiceGPS.newIntent(mActivity));
            mActivity.stopService(QrScanService.newIntent(mActivity));
        } else {
            mActivity.startService(ServiceGPS.newIntent(mActivity));
            mActivity.startService(QrScanService.newIntent(mActivity));
        }
    }

    public boolean checkPermissions()
    {
        boolean result = (ContextCompat.checkSelfPermission(mActivity, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) &&
                ((ContextCompat.checkSelfPermission(mActivity, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) ||
                        (ContextCompat.checkSelfPermission(mActivity, Manifest.permission.ACCESS_COARSE_LOCATION)
                                == PackageManager.PERMISSION_GRANTED));

        return result;
    }

    public void requestPermissions() {
        ActivityCompat.requestPermissions(mActivity,
                new String[]{ Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.CAMERA},
                REQUEST_PERMISSIONS);
    }

}