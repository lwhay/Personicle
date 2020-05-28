package personicle.datagen.nosqlcomp.sensoring.sensorinMI;

import asterix.recordV2.wrapper.Uuid;
import com.alibaba.fastjson.JSONObject;
import personicle.datagen.nosqlcomp.sensoring.sensoringUS.SensoringUS;

public class SensoringMIAlone {
    private String deviceId;
    private Long timestamp;
    private String userName;
    private int heartrate;
    private int stepcount;
    private String comments;

    public SensoringMIAlone(SensoringMI p) {
        this.deviceId = p.getDeviceId();
        this.timestamp = p.getTimestamp();
        this.userName = p.getUserName();
        this.heartrate = p.getHeartrate();
        this.stepcount = p.getStepcount();
        this.comments = p.getComments();
    }

    public SensoringMIAlone() {
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

    public int getHeartrate() {
        return heartrate;
    }

    public void setHeartrate(int heartrate) {
        this.heartrate = heartrate;
    }

    public int getStepcount() {
        return stepcount;
    }

    public void setStepcount(int stepcount) {
        this.stepcount = stepcount;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String toJSONString() {
        return JSONObject.toJSONString(this);
    }
}
