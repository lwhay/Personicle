package personicle.datagen.nosqlcomp.commonFileMeasurement;

import asterix.recordV2.wrapper.Uuid;
import com.alibaba.fastjson.JSONObject;

public class CommonFileMeasurementAlone {
    private String deviceId;
    private Long timestamp;
    private String userName;
    private String file_category;
    private String filepath;
    private String comments;

    public CommonFileMeasurementAlone(CommonFileMeasurement p) {
        this.deviceId = p.getDeviceId();
        this.timestamp = p.getTimestamp();
        this.userName = p.getUserName();
        this.file_category = p.getFile_category();
        this.filepath = p.getFilepath();
        this.comments = p.getComments();
    }

    public CommonFileMeasurementAlone() {
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

    public String getFile_category() {
        return file_category;
    }

    public void setFile_category(String file_category) {
        this.file_category = file_category;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String toJSONString() {
        return JSONObject.toJSONString(this);
    }
}
