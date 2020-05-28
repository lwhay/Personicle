package personicle.datagen.nosqlcomp.bracelet.braceletRawList;

import asterix.recordV2.wrapper.Uuid;
import com.alibaba.fastjson.JSONObject;
import personicle.datagen.nosqlcomp.GeneralMeasurement;

public class BraceletRawListAlone {
    private String deviceId;
    private Long timestamp;
    private String userName;
    private int netId;
    private String mac;
    private String tag;
    private Long startTime;
    private Long endTime;
    private int count;
    private String rawDetailUrl;
    private String dateString;
    private String status;
    private Long createTime;
    private Long updateTime;

    public BraceletRawListAlone(BraceletRawList braceletRawList) {
        this.deviceId = braceletRawList.getDeviceId();
        this.timestamp = braceletRawList.getTimestamp();
        this.userName = braceletRawList.getUserName();
        this.netId = braceletRawList.getNetId();
        this.mac = braceletRawList.getMac();
        this.tag = braceletRawList.getTag();
        this.startTime = braceletRawList.getStartTime();
        this.endTime = braceletRawList.getEndTime();
        this.count = braceletRawList.getCount();
        this.rawDetailUrl = braceletRawList.getRawDetailUrl();
        this.dateString = braceletRawList.getDateString();
        this.status = braceletRawList.getStatus();
        this.createTime = braceletRawList.getCreateTime();
        this.updateTime = braceletRawList.getUpdateTime();
    }

    public BraceletRawListAlone() {

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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getRawDetailUrl() {
        return rawDetailUrl;
    }

    public void setRawDetailUrl(String rawDetailUrl) {
        this.rawDetailUrl = rawDetailUrl;
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
