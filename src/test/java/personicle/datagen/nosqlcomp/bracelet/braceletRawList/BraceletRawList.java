package personicle.datagen.nosqlcomp.bracelet.braceletRawList;

import personicle.datagen.nosqlcomp.GeneralMeasurement;

public class BraceletRawList extends GeneralMeasurement {
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
}
