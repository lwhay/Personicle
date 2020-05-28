package personicle.datagen.nosqlcomp.bracelet.braceletHbList;

import asterix.recordV2.wrapper.Uuid;
import com.alibaba.fastjson.JSONObject;
import personicle.datagen.nosqlcomp.GeneralMeasurement;

public class BraceletHbListAlone {
    private String deviceId;
    private Long timestamp;
    private String userName;
    private int netId;
    private String mac;
    private int dbp;
    private int sbp;
    private int heartRate;
    private String dateString;
    private String status;
    private Long createTime;
    private Long updateTime;

    public BraceletHbListAlone(BraceletHbList braceletHbList) {
        this.deviceId = braceletHbList.getDeviceId();
        this.timestamp = braceletHbList.getTimestamp();
        this.userName = braceletHbList.getUserName();
        this.netId = braceletHbList.getNetId();
        this.mac = braceletHbList.getMac();
        this.dbp = braceletHbList.getDbp();
        this.sbp = braceletHbList.getSbp();
        this.heartRate = braceletHbList.getHeartRate();
        this.dateString = braceletHbList.getDateString();
        this.status = braceletHbList.getStatus();
        this.createTime = braceletHbList.getCreateTime();
        this.updateTime = braceletHbList.getUpdateTime();
    }

    public BraceletHbListAlone() {
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Uuid uuid) {
        this.deviceId = uuid.getUuid().toString().replace("-", "");
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

    public int getNetId() {
        return netId;
    }

    public void setNetId(int netId) {
        this.netId = netId;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public int getDbp() {
        return dbp;
    }

    public void setDbp(int dbp) {
        this.dbp = dbp;
    }

    public int getSbp() {
        return sbp;
    }

    public void setSbp(int sbp) {
        this.sbp = sbp;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String toJSONString() {
        return JSONObject.toJSONString(this);
    }
}
