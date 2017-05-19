package bigdata.sg.com.citytrafficdriverapp.Activities;

import com.google.zxing.Result;

import android.app.KeyguardManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import bigdata.sg.com.citytrafficdriverapp.App;
import bigdata.sg.com.citytrafficdriverapp.Config;
import bigdata.sg.com.citytrafficdriverapp.DataWriter;
import bigdata.sg.com.citytrafficdriverapp.Events.EventHelper;
import bigdata.sg.com.citytrafficdriverapp.QueryPreferences;
import bigdata.sg.com.citytrafficdriverapp.Utils.DateProvider;
import bigdata.sg.com.citytrafficdriverapp.database.Entities.AuthData;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QrCodeScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    public static final String TAG = "QrCodeScannerActivity";
    private ZXingScannerView mScannerView;
    private DataWriter mDataWriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        mDataWriter = ((App) getApplication()).getDataWriter();
        unlockScreen();
    }

    private void unlockScreen(){
        KeyguardManager mKM = (KeyguardManager) getSystemService(this.KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock mKL = mKM.newKeyguardLock("");
        mKL.disableKeyguard();

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera(0);
        mScannerView.setAutoFocus(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        Log.d(TAG, result.getText());
        if (!result.getText().isEmpty()) {
            String currentDate = DateProvider.getCurrentDate(Config.DATE_FORMAT);
            AuthData authData = new AuthData(currentDate, result.getText(), null, AuthData.LOGIN);

            QueryPreferences.setQrValue(this, result.getText());

            mDataWriter.write(authData);

            EventHelper.postAuthEvent(result.getText(), AuthData.LOGIN);
        }

        finish();
    }
}
