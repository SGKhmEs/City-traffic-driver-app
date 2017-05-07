package bigdata.sg.com.citytrafficdriverapp;

import com.google.zxing.Result;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;


import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.media.MediaRecorder.VideoSource.CAMERA;



public class QrCodeScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    public static final String TAG = "QrCodeScannerActivity";
    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);

        KeyguardManager mKM = (KeyguardManager) getSystemService(this.KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock mKL = mKM.newKeyguardLock("");
        mKL.disableKeyguard();

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //checkPermission();



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

    private void checkPermission(){
        // TODO: 05.05.2017 update camera permission
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{ Manifest.permission.CAMERA},
                        CAMERA);
            }
        }
    }

    @Override
    public void handleResult(Result result) {
        // TODO: 05.05.2017 add POST request
//        mScannerView.getForeground()
        Log.v(TAG, result.getText());
        Log.v(TAG, result.getBarcodeFormat().toString());
        finish();
    }
}
