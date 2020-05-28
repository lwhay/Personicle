package personicle.datagen.nosqlcomp.sensoring.sensoringCP;

import asterix.recordV2.wrapper.Uuid;
import com.alibaba.fastjson.JSONObject;
import personicle.datagen.nosqlcomp.emotion.emotionText.EmotionText;
import personicle.datagen.nosqlcomp.sensoring.Spatial3DPoint;

public class SensoringCPAlone {
    private String deviceId;
    private Long timestamp;
    private String userName;
    private Spatial3DPoint[] accelerometer;
    private Spatial3DPoint[] gyroscope;
    private String comments;

    public SensoringCPAlone(SensoringCP sensoringCP) {
        this.deviceId = sensoringCP.getDeviceId();
        this.timestamp = sensoringCP.getTimestamp();
        this.userName = sensoringCP.getUserName();
        this.accelerometer = sensoringCP.getAccelerometer();
        this.gyroscope = sensoringCP.getGyroscope();
    }

    public SensoringCPAlone() {
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

    public String getComments() {
        return comments;
    }

    public Spatial3DPoint[] getAccelerometer() {
        return accelerometer;
    }

    public void setAccelerometer(Spatial3DPoint[] accelerometer) {
        this.accelerometer = accelerometer;
    }

    public Spatial3DPoint[] getGyroscope() {
        return gyroscope;
    }

    public void setGyroscope(Spatial3DPoint[] gyroscope) {
        this.gyroscope = gyroscope;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String toJSONString() {
        return JSONObject.toJSONString(this);
    }
}
