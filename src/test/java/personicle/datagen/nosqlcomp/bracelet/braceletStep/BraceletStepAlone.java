package personicle.datagen.nosqlcomp.bracelet.braceletStep;


import asterix.recordV2.wrapper.Uuid;
import com.alibaba.fastjson.JSONObject;

public class BraceletStepAlone {
    private String deviceId;
    private Long timestamp;
    private String userName;
    private int netId;
    private String mac;
    private String date;
    private int sec;
    private int runtime;
    private int step;
    private String stepsInHour;
    private String dateString;
    private String status;
    private Long createTime;
    private Long updateTime;

    public BraceletStepAlone(BraceletStep braceletStep) {
        this.deviceId = braceletStep.getDeviceId();
        this.timestamp = braceletStep.getTimestamp();
        this.userName = braceletStep.getUserName();
        this.netId = braceletStep.getNetId();
        this.mac = braceletStep.getMac();
        this.date = braceletStep.getDate();
        this.sec = braceletStep.getSec();
        this.runtime = braceletStep.getRuntime();
        this.step = braceletStep.getStep();
        this.stepsInHour = braceletStep.getStepsInHour();
        this.dateString = braceletStep.getDateString();
        this.status = braceletStep.getStatus();
        this.createTime = braceletStep.getCreateTime();
        this.updateTime = braceletStep.getUpdateTime();
    }

    public BraceletStepAlone() {
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

    public String toJSONString() {
        return JSONObject.toJSONString(this);
    }
}
