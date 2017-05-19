package bigdata.sg.com.citytrafficdriverapp.Events;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by bonar on 5/19/2017.
 */

public class EventHelper {

    public static void postAuthEvent(String qrValue, boolean isLogin) {
        EventBus.getDefault().post(new AuthEvent(qrValue, isLogin));
    }

}
