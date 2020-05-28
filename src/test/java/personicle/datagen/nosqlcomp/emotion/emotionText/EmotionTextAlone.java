package personicle.datagen.nosqlcomp.emotion.emotionText;

import asterix.recordV2.wrapper.Uuid;
import com.alibaba.fastjson.JSONObject;
import personicle.datagen.nosqlcomp.emotion.emotionECG.EmotionECG;

public class EmotionTextAlone {
    private String deviceId;
    private Long timestamp;
    private String userName;
    private String message;
    private String comments;

    public EmotionTextAlone(EmotionText emotionText) {
        this.deviceId = emotionText.getDeviceId();
        this.timestamp = emotionText.getTimestamp();
        this.userName = emotionText.getUserName();
        this.message = emotionText.getMessage();
        this.comments = emotionText.getComments();
    }

    public EmotionTextAlone() {
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
