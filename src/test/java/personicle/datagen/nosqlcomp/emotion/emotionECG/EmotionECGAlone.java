package personicle.datagen.nosqlcomp.emotion.emotionECG;

import asterix.recordV2.wrapper.Uuid;
import com.alibaba.fastjson.JSONObject;

public class EmotionECGAlone {
    private String deviceId;
    private Long timestamp;
    private String userName;
    private Double[] payload;
    private String comments;

    public EmotionECGAlone(EmotionECG emotionECG) {
        this.deviceId = emotionECG.getDeviceId();
        this.timestamp = emotionECG.getTimestamp();
        this.userName = emotionECG.getUserName();
        this.payload = emotionECG.getPayload();
        this.comments = emotionECG.getComments();
    }

    public EmotionECGAlone() {
    }

    public Double[] getPayload() {
        return payload;
    }

    public void setPayload(Double[] payload) {
        this.payload = payload;
    }

    public String  getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Uuid uuid) {
        this.deviceId = uuid.getUuid().toString().replace("-", "");
        this.deviceId = this.deviceId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
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
