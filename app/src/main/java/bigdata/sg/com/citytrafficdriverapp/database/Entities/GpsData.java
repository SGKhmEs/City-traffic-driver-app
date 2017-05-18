package bigdata.sg.com.citytrafficdriverapp.database.Entities;

import android.location.Location;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Property;

@Entity(
        indexes = {
                @Index(value = "time ASC")
        }
)
public class GpsData {

    @Id
    private Long id;

    @Property
    private String time;

    @Property
    private String qrValue;

    @Property
    private Double latitude;
    @Property
    private Double longitude;
    @Property
    private Float speed;
    @Property
    private Float accuracy;

    @Override
    public String toString() {
        String result = "[id: " + id + ", time: " + time + ", qrValue: " + qrValue +
                ", latitude|longitude: " + latitude + "|" + longitude + ", speed: " + speed +
                ", accuracy: " + accuracy;
        return result;
    }

    public GpsData(Location location, String time, String qrValue) {
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
        this.speed = location.getSpeed();
        this.accuracy = location.getAccuracy();
        this.time = time;
        this.qrValue = qrValue;
    }

    @Generated(hash = 1434002210)
    public GpsData(Long id, String time, String qrValue, Double latitude,
            Double longitude, Float speed, Float accuracy) {
        this.id = id;
        this.time = time;
        this.qrValue = qrValue;
        this.latitude = latitude;
        this.longitude = longitude;
        this.speed = speed;
        this.accuracy = accuracy;
    }
    @Generated(hash = 1232323744)
    public GpsData() {
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
    public Double getLatitude() {
        return this.latitude;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    public Double getLongitude() {
        return this.longitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    public Float getSpeed() {
        return this.speed;
    }
    public void setSpeed(Float speed) {
        this.speed = speed;
    }
    public Float getAccuracy() {
        return this.accuracy;
    }
    public void setAccuracy(Float accuracy) {
        this.accuracy = accuracy;
    }
    public String getQrValue() {
        return this.qrValue;
    }
    public void setQrValue(String qrValue) {
        this.qrValue = qrValue;
    }


}
