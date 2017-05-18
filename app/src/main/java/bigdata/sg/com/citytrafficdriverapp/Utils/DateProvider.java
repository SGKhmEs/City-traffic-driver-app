package bigdata.sg.com.citytrafficdriverapp.Utils;

import android.annotation.SuppressLint;

import java.util.Calendar;
import java.text.SimpleDateFormat;

/**
 * Created by bonar on 5/18/2017.
 */

public class DateProvider {

    public static String getCurrentDate(String format) {
        Calendar c = Calendar.getInstance();

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        return sdf.format(c.getTime());
    }

}
