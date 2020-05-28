package personicle.datagen.nosqlcomp.bracelet.braceletHeartRateBloodPressure;

import personicle.datagen.nosqlcomp.GeneralMeasurement;

public class BraceletHeartRateBloodPressure extends GeneralMeasurement {
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
}
