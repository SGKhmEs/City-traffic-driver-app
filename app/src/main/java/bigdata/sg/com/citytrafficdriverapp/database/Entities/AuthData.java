package bigdata.sg.com.citytrafficdriverapp.database.Entities;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(
        indexes = {
                @Index(value = "time ASC")
        }
)
public class AuthData {
    public static final boolean LOGIN = true;
    public static final boolean LOGOUT = false;

    @Id
    private Long id;

    @Property
    private String time;

    @Property
    private String qrValue;

    @Property
    private String picturePath;

    @Property
    private boolean login;

    @Generated(hash = 106087593)
    public AuthData() {
    }

    @Generated(hash = 2050152063)
    public AuthData(Long id, String time, String qrValue, String picturePath,
            boolean login) {
        this.id = id;
        this.time = time;
        this.qrValue = qrValue;
        this.picturePath = picturePath;
        this.login = login;
    }

    public AuthData(String time, String qrValue, String picturePath, boolean login) {
        this.time = time;
        this.qrValue = qrValue;
        this.picturePath = picturePath;
        this.login = login;
    }

    @Override
    public String toString() {
        return "Qr value: " + getQrValue()
                + "  Time: " + getTime()
                + "  Picture path: " + getPicturePath()
                + "  Login: " + getLogin();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getQrValue() {
        return this.qrValue;
    }

    public void setQrValue(String qrValue) {
        this.qrValue = qrValue;
    }

    public String getPicturePath() {
        return this.picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public boolean getLogin() {
        return this.login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }
}
