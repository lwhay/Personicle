package personicle.datagen.nosqlcomp.invertedindex;

import asterix.recordV2.wrapper.DateTime;
import asterix.recordV2.wrapper.Uuid;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class IIFoodLog {
    private Uuid deviceId;
    private String userName;
    private String foodName;
    private Double weight;
    private Double total_calories;
    private Long timestamp;
    private List<Uuid> subEvents;   //subEvents: [uuid],    --sub events of event
    //    private Point location;         //location: point?,     --location of event
    private Double latitude;
    private Double longitude;
    private Integer preference_star;
    private String comments;
    // general attributes
    private Uuid measureId;
    private String category;
    private DateTime startAt;  //startAt: datetime, --start of event
    private DateTime endAt;
    //endAt: datetime, --end of event using `` because of a potential AsterixDB bugprivate DateTime startAt;
    private String attribute;
    //    private String activity;            //name: string,
    private String description;

    public Uuid getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Uuid deviceId) {
        this.deviceId = deviceId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getTotal_calories() {
        return total_calories;
    }

    public void setTotal_calories(Double total_calories) {
        this.total_calories = total_calories;
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

    public List<Uuid> getSubEvents() {
        return subEvents;
    }

    public void setSubEvents(List<Uuid> subEvents) {
        this.subEvents = subEvents;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getPreference_star() {
        return preference_star;
    }

    public void setPreference_star(Integer preference_star) {
        this.preference_star = preference_star;
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
