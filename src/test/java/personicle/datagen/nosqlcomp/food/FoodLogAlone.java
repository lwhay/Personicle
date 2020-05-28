package personicle.datagen.nosqlcomp.food;

import asterix.recordV2.wrapper.Uuid;
import com.alibaba.fastjson.JSONObject;

public class FoodLogAlone {
    private String deviceId;
    private Long timestamp;
    private String userName;
    private String foodName;
    private Double weight;
    private Double total_calories;
    private Double latitude;
    private Double longitude;
    private Integer preference_star;
    private String comments;

    public FoodLogAlone(FoodLog fl) {
        this.deviceId = fl.getDeviceId();
        this.timestamp = fl.getTimestamp();
        this.userName = fl.getUserName();
        this.foodName = fl.getFoodName();
        this.weight = fl.getWeight();
        this.total_calories = fl.getTotal_calories();
        this.latitude = fl.getLatitude();
        this.longitude = fl.getLongitude();
        this.preference_star = fl.getPreference_star();
        this.comments = fl.getComments();
    }

    public FoodLogAlone() {
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

    public String toJSONString() {
        return JSONObject.toJSONString(this);
    }
}
