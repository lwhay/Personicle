package personicle.datagen.nosqlcomp;

import asterix.recordV2.wrapper.DateTime;
import asterix.recordV2.wrapper.Uuid;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class GeneralMeasurement {
    private String measure;
    private String deviceId;
    private Long timestamp;
    private String userName;
    private DateTime startAt;  //startAt: datetime,    --start of event
    private DateTime endAt;
            //endAt: datetime,      --end of event using `` because of a potential AsterixDB bugprivate DateTime startAt;
    private String category;
    private String attribute;
    private String activity;            //name: string,
    private String description;

    public GeneralMeasurement(GeneralMeasurement gm) {
        this.measure = gm.getMeasure();
        this.deviceId = gm.getDeviceId();
        this.timestamp = gm.getTimestamp();
        this.userName = gm.getUserName();
        this.startAt = gm.getStartAt();
        this.endAt = gm.getEndAt();
        this.category = gm.getCategory();
        this.attribute = gm.getAttribute();
        this.activity = gm.getCategory();
        this.description = gm.getDescription();
    }

    public GeneralMeasurement() {
    }

    public String getDeviceId() {
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

    public DateTime getStartAt() {
        return startAt;
    }

    public void setStartAt(DateTime startAt) {
        this.startAt = startAt;
    }

    public DateTime getEndAt() {
        return endAt;
    }

    public void setEndAt(DateTime endAt) {
        this.endAt = endAt;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(Uuid uuid) {
        this.measure = uuid.getUuid().toString().replace("-", "");
        this.measure = this.measure.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(List<Uuid> attribute) {
        this.attribute = new String();
        for (Uuid uuid : attribute) {
            this.attribute += uuid.getUuid().toString().replace("-", "");
            this.attribute += " ";
        }
        this.attribute = this.attribute.trim();
    }

    public void setFoodAttribute(List<String> attribute) {
        this.attribute = new String();
        for (String string : attribute) {
            this.attribute += string;
            this.attribute += " ";
        }
        this.attribute = this.attribute.trim();
    }

    //    public String getActivity() {
    //        return activity;
    //    }
    //
    //    public void setActivity(String activity) {
    //        this.activity = activity;
    //    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toJSONString() {
        return JSONObject.toJSONString(this);
    }
}
