package bigdata.sg.com.citytrafficdriverapp.Events;

/**
 * Created by bonar on 5/19/2017.
 */

public class AuthEvent {

    public final String qrValue;
    public final boolean isLogin;

    public AuthEvent(String qrValue, boolean isLogin) {
        this.qrValue = qrValue;
        this.isLogin = isLogin;
    }
}
