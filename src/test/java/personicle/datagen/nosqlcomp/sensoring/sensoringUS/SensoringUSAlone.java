package personicle.datagen.nosqlcomp.sensoring.sensoringUS;

import asterix.recordV2.wrapper.Uuid;
import com.alibaba.fastjson.JSONObject;
import personicle.datagen.nosqlcomp.sensoring.sensoringGPS.SensoringGPS;

public class SensoringUSAlone {
    private String deviceId;
    private Long timestamp;
    private String userName;
    private String softinfo;
    private String comments;

    public SensoringUSAlone(SensoringUS p) {
        this.deviceId = p.getDeviceId();
        this.timestamp = p.getTimestamp();
        this.userName = p.getUserName();
        this.softinfo = p.getSoftinfo();
        this.comments = p.getComments();
    }

    public SensoringUSAlone() {
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

    public String getSoftinfo() {
        return softinfo;
    }

    public void setSoftinfo(String softinfo) {
        this.softinfo = softinfo;
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
