package personicle.datagen.nosqlcomp.bracelet.braceletStep;

import personicle.datagen.nosqlcomp.GeneralMeasurement;

public class BraceletStep extends GeneralMeasurement {
    private int netId;
    private String mac;
    private String date;
    private int sec;
    private int runtime;
    private int step;
    private String stepsInHour="[]";
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
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
