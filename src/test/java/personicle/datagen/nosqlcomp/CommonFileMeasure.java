package personicle.datagen.nosqlcomp;

import asterix.recordV2.wrapper.DateTime;
import asterix.recordV2.wrapper.Uuid;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class CommonFileMeasure {
    private Uuid deviceId;
    private String file_category;
    private String userName;
    private Long timestamp;
    private String filepath;
    private String comments;
    // general attributes
    private Uuid measureId;
    private String category;
    private DateTime startAt;  //startAt: datetime,    --start of event
    private DateTime endAt;    //endAt: datetime,      --end of event using `` because of a potential AsterixDB bug
    private List<Uuid> attribute;
    //    private String activity;            //name: string,
    private String description;
    //level: bigint?,       --Jordan's paper referred to different levels of daily activities.

    public Uuid getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Uuid deviceId) {
        this.deviceId = deviceId;
    }

    public String getFile_category() {
        return file_category;
    }

    public void setFile_category(String file_category) {
        this.file_category = file_category;
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

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Uuid getMeasureId() {
        return measureId;
    }

    public void setMeasureId(Uuid measureId) {
        this.measureId = measureId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public List<Uuid> getAttribute() {
        return attribute;
    }

    public void setAttribute(List<Uuid> attribute) {
        this.attribute = attribute;
    }

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
