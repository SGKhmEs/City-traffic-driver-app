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
    @Id
    private Long id;

    @Property
    private String time;

    @Property
    String qrValue;

    @Property
    private String picturePath;

    @Generated(hash = 112269877)
    public AuthData(Long id, String time, String qrValue, String picturePath) {
        this.id = id;
        this.time = time;
        this.qrValue = qrValue;
        this.picturePath = picturePath;
    }

    @Generated(hash = 106087593)
    public AuthData() {
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
}
