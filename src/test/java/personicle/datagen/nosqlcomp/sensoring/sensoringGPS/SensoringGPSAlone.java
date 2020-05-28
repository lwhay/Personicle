package personicle.datagen.nosqlcomp.sensoring.sensoringGPS;

import asterix.recordV2.wrapper.Uuid;
import com.alibaba.fastjson.JSONObject;
import personicle.datagen.nosqlcomp.sensoring.Spatial3DPoint;
import personicle.datagen.nosqlcomp.sensoring.sensoringCP.SensoringCP;

public class SensoringGPSAlone {
    private String deviceId;
    private Long timestamp;
    private String userName;
    private int weatherInfo;
    private Double[] latitude;
    private Double[] longitude;
    private String comments;

    public SensoringGPSAlone(SensoringGPS p) {
        this.deviceId = p.getDeviceId();
        this.timestamp = p.getTimestamp();
        this.userName = p.getUserName();
        this.weatherInfo = p.getWeatherInfo();
        this.latitude = p.getLatitude();
        this.longitude = p.getLongitude();
        this.comments = p.getComments();
    }

    public SensoringGPSAlone() {
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Uuid deviceId) {
        this.deviceId = deviceId.getUuid().toString().replace("-", "");
        this.deviceId = this.deviceId.trim();
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getWeatherInfo() {
        return weatherInfo;
    }

    public void setWeatherInfo(int weatherInfo) {
        this.weatherInfo = weatherInfo;
    }

    public Double[] getLatitude() {
        return latitude;
    }

    public void setLatitude(Double[] latitude) {
        this.latitude = latitude;
    }

    public Double[] getLongitude() {
        return longitude;
    }

    public void setLongitude(Double[] longitude) {
        this.longitude = longitude;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String toJSONString() {
        return JSONObject.toJSONString(this);
    }
}
