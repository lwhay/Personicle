package personicle.datagen.nosqlcomp.bracelet.braceletHeartRateBloodPressure;

import asterix.recordV2.wrapper.Uuid;
import com.alibaba.fastjson.JSONObject;
import personicle.datagen.nosqlcomp.GeneralMeasurement;

public class BraceletHeartRateBloodPressureAlone {
    private String deviceId;
    private Long timestamp;
    private String userName;
    private int netId;
    private String mac;
    private int mainSec;
    private int mainDate;
    private int hour;
    private int min;
    private int sec;
    private int hrm;
    private int sbp;
    private int dbp;
    private int spo2;
    private int fatigue;
    private int rsv3;
    private String dateString;
    private String status;
    private Long createTime;
    private Long updateTime;

    public BraceletHeartRateBloodPressureAlone(BraceletHeartRateBloodPressure braceletHeartRateBloodPressure) {
        this.deviceId = braceletHeartRateBloodPressure.getDeviceId();
        this.timestamp = braceletHeartRateBloodPressure.getTimestamp();
        this.userName = braceletHeartRateBloodPressure.getUserName();
        this.netId = braceletHeartRateBloodPressure.getNetId();
        this.mac = braceletHeartRateBloodPressure.getMac();
        this.mainSec = braceletHeartRateBloodPressure.getMainSec();
        this.mainDate = braceletHeartRateBloodPressure.getMainDate();
        this.hour = braceletHeartRateBloodPressure.getHour();
        this.min = braceletHeartRateBloodPressure.getMin();
        this.sec = braceletHeartRateBloodPressure.getSec();
        this.hrm = braceletHeartRateBloodPressure.getHrm();
        this.sbp = braceletHeartRateBloodPressure.getSbp();
        this.dbp = braceletHeartRateBloodPressure.getDbp();
        this.spo2 = braceletHeartRateBloodPressure.getSpo2();
        this.fatigue = braceletHeartRateBloodPressure.getFatigue();
        this.rsv3 = braceletHeartRateBloodPressure.getRsv3();
        this.dateString = braceletHeartRateBloodPressure.getDateString();
        this.status = braceletHeartRateBloodPressure.getStatus();
        this.createTime = braceletHeartRateBloodPressure.getCreateTime();
        this.updateTime = braceletHeartRateBloodPressure.getUpdateTime();
    }

    public BraceletHeartRateBloodPressureAlone() {
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

    public int getMainSec() {
        return mainSec;
    }

    public void setMainSec(int mainSec) {
        this.mainSec = mainSec;
    }

    public int getMainDate() {
        return mainDate;
    }

    public void setMainDate(int mainDate) {
        this.mainDate = mainDate;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    public int getHrm() {
        return hrm;
    }

    public void setHrm(int hrm) {
        this.hrm = hrm;
    }

    public int getSbp() {
        return sbp;
    }

    public void setSbp(int sbp) {
        this.sbp = sbp;
    }

    public int getDbp() {
        return dbp;
    }

    public void setDbp(int dbp) {
        this.dbp = dbp;
    }

    public int getSpo2() {
        return spo2;
    }

    public void setSpo2(int spo2) {
        this.spo2 = spo2;
    }

    public int getFatigue() {
        return fatigue;
    }

    public void setFatigue(int fatigue) {
        this.fatigue = fatigue;
    }

    public int getRsv3() {
        return rsv3;
    }

    public void setRsv3(int rsv3) {
        this.rsv3 = rsv3;
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
