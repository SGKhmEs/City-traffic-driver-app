package bigdata.sg.com.citytrafficdriverapp;

import android.content.Context;
import android.preference.PreferenceManager;

public class QueryPreferences {
    public static final String QR_VALUE = "qrCodeValue";

    public static String getQrValue(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(QR_VALUE, null);
    }

    public static void setQrValue(Context context, String query){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(QR_VALUE, query)
                .apply();
    }
}
