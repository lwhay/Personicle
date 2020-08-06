package personicle.datagen.nosqlcomp.bracelet.braceletSleep;

import asterix.recordV2.wrapper.DateTime;
import personicle.datagen.nosqlcomp.GeneralMeasurement;

public class BraceletSleep extends GeneralMeasurement {
    private int netId;
    private String mac;
    private DateTime date;
    private Long startTime = 0L;
    private Long endTime = 0L;
    private int runMin;
    private int restlessMin;
    private int deepMin;
    private String stepsInHour = "[]";
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

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public int getRunMin() {
        return runMin;
    }

    public void setRunMin(int runMin) {
        this.runMin = runMin;
    }

    public int getRestlessMin() {
        return restlessMin;
    }

    public void setRestlessMin(int restlessMin) {
        this.restlessMin = restlessMin;
    }

    public int getDeepMin() {
        return deepMin;
    }

    public void setDeepMin(int deepMin) {
        this.deepMin = deepMin;
    }

    public String getStepsInHour() {
        return stepsInHour;
    }

    public void setStepsInHour(String stepsInHour) {
        this.stepsInHour = stepsInHour;
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
